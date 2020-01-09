#include "AggregationConfig.h"

void cf::to_json(json &j, const cf::AggregationConfig &obj) {
    j = json{
            {"ids",     obj.ids},
            {"types",   obj.types},
            {"firstId", obj.firstId},
    };
}

void cf::from_json(const json &j, cf::AggregationConfig &obj) {
    j.at("ids").get_to(obj.ids);
    j.at("types").get_to(obj.types);
    j.at("firstId").get_to(obj.firstId);
}