#include "IndexDTO.h"

cf::IndexDTO::IndexDTO(const std::string &type, const std::vector <Entry<double>> &entries) : type(type), entries(entries) {}

void cf::to_json(json &j, const cf::IndexDTO &obj) {
    j = json{
            {"type",    obj.type},
            {"entries", obj.entries}
    };
}

void cf::from_json(const json &j, cf::IndexDTO &obj) {
    j.at("type").get_to(obj.type);
    j.at("entries").get_to(obj.entries);
}
