#ifndef ZPR_AGGREGATIONCOORDINATOR_H
#define ZPR_AGGREGATIONCOORDINATOR_H

#include <string>
#include <unordered_map>
#include <memory>
#include <model/Stat.h>
#include <model/Currency.h>
#include "config/AggregationConfig.h"
#include "aggregation/AggregatingBuffer.h"

class AggregationCoordinator : public Observer {
public:
    using PBuffer = std::shared_ptr<AggregatingBuffer<std::shared_ptr<Data>>>;

    explicit AggregationCoordinator(const cf::AggregationConfig &config);

    void onInit();

    void update(const std::string &name) override;

    std::map<std::string, Stat> update(const std::shared_ptr<Data> &data);

private:
    std::unordered_map<std::string, std::string> successors_;
    std::unordered_map<std::string, PBuffer> buffers_;
    std::string first_;
    std::map<std::string, Stat> updateResult_;

    PBuffer createBuffer(size_t size, const std::string &key, const std::shared_ptr<Data> &defVal) const;

};

#endif //ZPR_AGGREGATIONCOORDINATOR_H