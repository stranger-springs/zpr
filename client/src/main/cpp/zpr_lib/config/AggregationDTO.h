#ifndef ZPR_AGGREGATIONDTO_H
#define ZPR_AGGREGATIONDTO_H

#include <nlohmann/json.hpp>
#include "StatDTO.h"
#include "Entry.h"

using nlohmann::json;

namespace cf {
    struct AggregationDTO {
        AggregationDTO() = default;

        AggregationDTO(std::vector<cf::Entry<cf::StatDTO>> entries);

        std::vector<Entry<StatDTO>> result;
    };

    void to_json(json &j, const AggregationDTO &obj);

    void from_json(const json &j, AggregationDTO &obj);
}

#endif //ZPR_AGGREGATIONDTO_H
