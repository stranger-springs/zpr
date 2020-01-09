#include "StatDTO.h"

cf::StatDTO::StatDTO(const std::string &id, const std::vector<Entry<double>> &stats) : id(id), stats(stats) {}

void cf::to_json(json &j, const StatDTO &obj) {
    j = json{
            {"id",    obj.id},
            {"stats", obj.stats},
    };
}

void cf::from_json(const json &j, cf::StatDTO &obj) {
    j.at("id").get_to(obj.id);
    j.at("stats").get_to(obj.stats);
}
