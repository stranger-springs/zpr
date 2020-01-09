#ifndef ZPR_AGGREGATION_H
#define ZPR_AGGREGATION_H

#include <nlohmann/json.hpp>

using nlohmann::json;

namespace cf {
    struct Aggregation {
        std::string id;
        int value;
        std::string next;
    };

    void to_json(json &j, const Aggregation &obj);

    void from_json(const json &j, Aggregation &obj);
}

#endif //ZPR_AGGREGATION_H
