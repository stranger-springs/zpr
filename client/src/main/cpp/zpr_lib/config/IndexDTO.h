#ifndef ZPR_INDEXDTO_H
#define ZPR_INDEXDTO_H

#include <nlohmann/json.hpp>
#include "Entry.h"

using nlohmann::json;

namespace cf {
    struct IndexDTO {
        std::string type;
        std::vector<Entry<double>> entries;

        IndexDTO() {}

        IndexDTO(const std::string &type, const std::vector<Entry<double>> &entries);
    };

    void to_json(json &j, const IndexDTO &obj);

    void from_json(const json &j, IndexDTO &obj);
}

#endif //ZPR_INDEXDTO_H
