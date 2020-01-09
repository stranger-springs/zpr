#include "Aggregation.h"

void cf::to_json(json &j, const cf::Aggregation &obj) {
    j = json{
            {"id",    obj.id},
            {"value", obj.value},
            {"next",  obj.next}
    };
}

void cf::from_json(const json &j, cf::Aggregation &obj) {
    j.at("id").get_to(obj.id);
    j.at("value").get_to(obj.value);
    j.at("next").get_to(obj.next);
}


