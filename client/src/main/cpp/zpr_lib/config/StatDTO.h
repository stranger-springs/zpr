#ifndef ZPR_STATDTO_H
#define ZPR_STATDTO_H

#include <nlohmann/json.hpp>
#include "config/Entry.h"

using nlohmann::json;

namespace cf {
    struct StatDTO {
        std::string id;
        std::vector<Entry<double>> stats;
        long firstId;
        long lastId;

        StatDTO() {}

        StatDTO(const std::string &id, long firstId, long lastId, const std::vector<Entry<double>> &stats);
    };

    void to_json(json &j, const StatDTO &obj);

    void from_json(const json &j, StatDTO &obj);
}

#endif //ZPR_STATDTO_H
