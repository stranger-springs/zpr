#ifndef ZPR_INDEX_H
#define ZPR_INDEX_H

#include <vector>
#include <string>
#include <nlohmann/json.hpp>
#include "Entry.h"

using nlohmann::json;

namespace cf {
    struct Index {
        std::string type;
        std::vector<Entry<int>> entries;
    };

    void to_json(json &j, const Index &obj);

    void from_json(const json &j, Index &obj);
}


#endif //ZPR_INDEX_H
