#include "IndexConfig.h"

void cf::to_json(json &j, const IndexConfig &obj) {
    j = json{
            {"types",   obj.types},
            {"indexes", obj.indexes}
    };
}

void cf::from_json(const json &j, IndexConfig &obj) {
    j.at("types").get_to(obj.types);
    j.at("indexes").get_to(obj.indexes);
}
