#ifndef ZPR_AGGREGATIONCONFIG_H
#define ZPR_AGGREGATIONCONFIG_H

#include <vector>
#include <string>
#include <nlohmann/json.hpp>
#include "Aggregation.h"

using nlohmann::json;

namespace cf {
    struct AggregationConfig {
        std::vector<std::string> ids;
        std::vector<Aggregation> types;
        std::string firstId;
    };

    void to_json(json &j, const AggregationConfig &obj);

    void from_json(const json &j, AggregationConfig &obj);
}

#endif //ZPR_AGGREGATIONCONFIG_H
