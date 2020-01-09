#include "Index.h"

void cf::to_json(json &j, const Index &obj) {
    j = json{
            {"type",    obj.type},
            {"entries", obj.entries}
    };
}

void cf::from_json(const json &j, Index &obj) {
    j.at("type").get_to(obj.type);
    j.at("entries").get_to(obj.entries);
}
