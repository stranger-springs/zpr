#include "Config.h"

void cf::to_json(json &j, const Config &obj) {
    j = json{
            {"currencies",        obj.currencies},
            {"indexConfig",       obj.indexConfig},
            {"aggregationConfig", obj.aggregationConfig},
    };
}

void cf::from_json(const json &j, Config &c) {
    j.at("currencies").get_to(c.currencies);
    j.at("indexConfig").get_to(c.indexConfig);
    j.at("aggregationConfig").get_to(c.aggregationConfig);
}