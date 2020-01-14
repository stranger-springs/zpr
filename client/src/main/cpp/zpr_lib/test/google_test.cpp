
#include <gtest/gtest.h>
#include <config/AggregationDTO.h>
#include <model/ModelProxy.h>
#include <config/JsonUtils.h>
#include <test/TestUtils.h>
#include <index/RealtimeIndexFactory.h>
#include "config/Config.h"

using nlohmann::json;

TEST(JsonConfigParseTest, ExampleJson) {
    std::string jsonString = TestUtils::loadStringFromFile("TestConfig2.json");
    cf::Config conf = cf::JsonUtils<cf::Config>::fromJSON(jsonString);

    EXPECT_EQ(conf.currencies.size(), 2);
    EXPECT_EQ(conf.currencies[0], "bitcoin");
    EXPECT_EQ(conf.currencies[1], "zcash");

    cf::IndexConfig indexConfig = conf.indexConfig;

    EXPECT_EQ(indexConfig.indexes.size(), 2);
    cf::Index index = indexConfig.indexes[0];
    EXPECT_EQ(index.type, "ema");
    EXPECT_EQ(index.entries.size(), 1);
    EXPECT_EQ(index.entries[0].key, "ema5");
    EXPECT_EQ(index.entries[0].value, 5);

    cf::AggregationConfig aggregationConfig = conf.aggregationConfig;
    EXPECT_EQ(aggregationConfig.ids.size(), 3);
    EXPECT_EQ(aggregationConfig.firstId, "a");
    EXPECT_EQ(aggregationConfig.types.size(), 3);
    EXPECT_EQ(aggregationConfig.types[0].id, "a");
    EXPECT_EQ(aggregationConfig.types[0].value, 5);
    EXPECT_EQ(aggregationConfig.types[0].next, "b");
}

TEST(AggregationDTOTest, ExampleJson) {

    cf::AggregationDTO aggregationDto;
    std::vector<cf::Entry<double>> values = {
            {cf::make_entry("a", 1.1)},
            {cf::make_entry("b", 1.2)},
    };

    aggregationDto.result.push_back(cf::make_entry("currency1", cf::StatDTO("minutes", values)));
    aggregationDto.result.push_back(cf::make_entry("currency2", cf::StatDTO("minutes", values)));

    json j = aggregationDto;
    std::cout << j.dump(4) << std::endl;
    EXPECT_EQ(j["result"].size(), 2);
    EXPECT_EQ(j["result"][0]["key"], "currency1");
    EXPECT_EQ(j["result"][1]["key"], "currency2");
    EXPECT_EQ(j["result"][0]["value"]["id"], "minutes");
    EXPECT_EQ(j["result"][0]["value"]["stats"][0]["key"], "a");
    EXPECT_EQ(j["result"][0]["value"]["stats"][0]["value"], 1.1);
}

TEST(AggregationTest, ABC) {
    std::string jsonConfig = TestUtils::loadStringFromFile("TestConfig2.json");
    ModelProxy &proxy = ModelProxy::getInstance(jsonConfig);

    std::string updateInput = TestUtils::loadStringFromFile("TestInput.json");

    std::vector<std::string> names = cf::JsonUtils<cf::Config>::fromJSON(jsonConfig).currencies;

    for (int i = 0; i < 4; i++) {
        std::string updateIndexResult = proxy.updateIndex(updateInput);
        std::string updateAggregationResult = proxy.updateAggregation();
        cf::IndexesDTO indexesDto = cf::JsonUtils<cf::IndexesDTO>::fromJSON(updateIndexResult);
        cf::AggregationDTO aggregationDto = cf::JsonUtils<cf::AggregationDTO>::fromJSON(updateAggregationResult);

        EXPECT_EQ(indexesDto.indexes.size(), 2);
        EXPECT_EQ(aggregationDto.result.size(), 0);
    }


    cf::IndexesDTO indexesDto = cf::JsonUtils<cf::IndexesDTO>::fromJSON(proxy.updateIndex(updateInput));
    cf::AggregationDTO aggregationDto = cf::JsonUtils<cf::AggregationDTO>::fromJSON(proxy.updateAggregation());
    json j = aggregationDto;
    std::cout << j.dump(4) << std::endl;
    EXPECT_EQ(indexesDto.indexes.size(), 2);
    EXPECT_EQ(aggregationDto.result.size(), 2);
}

TEST(AggregationCoordinatorTest, ABC) {
    std::string firstId = "a";
    int firstSize = 3;
    std::string nextId = "b";
    int nextSize = 3;

    cf::AggregationConfig config;
    config.ids = {firstId, nextId};
    config.firstId = firstId;

    cf::Aggregation aggregation1;
    aggregation1.id = firstId;
    aggregation1.next = nextId;
    aggregation1.value = firstSize;

    cf::Aggregation aggregation2;
    aggregation2.id = nextId;
    aggregation2.next = "";
    aggregation2.value = nextSize;

    config.types = {aggregation1, aggregation2};

    AggregationCoordinator coordinator(config);
    coordinator.onInit();
    std::vector<double> values = {10, 20, 30,
                                  40, 50, 60,
                                  15, 20, 25};
    std::vector<int> sizes = {0, 0, 1,
                              0, 0, 1,
                              0, 0, 2};

    std::vector<std::map<std::string, Stat>> results = {
            {{"a", Stat(2, 0, 2, 20, 10, 30)}},
            {{"a", Stat(5, 3, 5, 50, 40, 60)}},
            {
             {"a", Stat(8, 3, 5, 20, 15, 25)},
                    {"b", Stat(8, 2, 8, 30, 20, 50)}
            },
    };


    int numOfUpdates = 0;
    for (int i = 0; i < values.size(); ++i) {
        auto updateResult = coordinator.update(std::make_shared<Currency>(i, values[i]));
        int updateResultSize = updateResult.size();
        EXPECT_EQ(updateResultSize, sizes[i]);
        if (updateResultSize != 0) {
            std::map<std::string, Stat> expectedResult = results[numOfUpdates++];
            for (const auto &pair : updateResult) {
                auto element = expectedResult.find(pair.first);
                EXPECT_TRUE(element != expectedResult.end());
                Stat expectedStat = element->second;
                EXPECT_EQ(pair.second.getID(), expectedStat.getID());
                EXPECT_EQ(pair.second.getMax(), expectedStat.getMax());
                EXPECT_EQ(pair.second.getMin(), expectedStat.getMin());
                EXPECT_EQ(pair.second.getAvg(), expectedStat.getAvg());
            }
        }
    }
}
