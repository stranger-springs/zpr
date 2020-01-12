#include "StatDTO.h"

cf::StatDTO::StatDTO(const std::string &id, long firstId, long lastId, const std::vector<Entry<double>> &stats)
        : id(id), firstId(firstId), lastId(lastId), stats(stats) {}

void cf::to_json(json &j, const StatDTO &obj) {
    j = json{
            {"id",      obj.id},
            {"stats",   obj.stats},
            {"firstId", obj.firstId},
            {"lastId",  obj.lastId},
    };
}

void cf::from_json(const json &j, cf::StatDTO &obj) {
    j.at("id").get_to(obj.id);
    j.at("stats").get_to(obj.stats);
    j.at("firstId").get_to(obj.firstId);
    j.at("lastId").get_to(obj.lastId);
}
