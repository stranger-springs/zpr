#ifndef ZPR_INDEXCONFIG_H
#define ZPR_INDEXCONFIG_H

#include <vector>
#include <string>
#include <nlohmann/json.hpp>
#include "Index.h"

using nlohmann::json;

namespace cf {
    struct IndexConfig {
        std::vector<std::string> types;
        std::vector<Index> indexes;
    };

    void to_json(json &j, const IndexConfig &obj);

    void from_json(const json &j, IndexConfig &obj);
}

#endif //ZPR_INDEXCONFIG_H
