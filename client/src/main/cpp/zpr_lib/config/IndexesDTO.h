#ifndef ZPR_INDEXESDTO_H
#define ZPR_INDEXESDTO_H

#include <nlohmann/json.hpp>
#include "IndexDTO.h"

using nlohmann::json;

namespace cf {
    struct IndexesDTO {
        std::vector<IndexDTO> indexes;

        IndexesDTO();
        IndexesDTO(const std::vector<IndexDTO> &indexes);
    };

    void to_json(json &j, const IndexesDTO &obj);

    void from_json(const json &j, IndexesDTO &obj);
}

#endif //ZPR_INDEXESDTO_H
