#ifndef ZPR_CONFIG_H
#define ZPR_CONFIG_H

#include <vector>
#include <string>
#include <nlohmann/json.hpp>
#include "Index.h"
#include "AggregationConfig.h"
#include "IndexConfig.h"

using nlohmann::json;

namespace cf {
    struct Config {
        std::vector<std::string> currencies;
        IndexConfig indexConfig;
        AggregationConfig aggregationConfig;
    };

    void to_json(json &j, const Config &obj);

    void from_json(const json &j, Config &c);
}


#endif //ZPR_CONFIG_H
