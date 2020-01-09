#ifndef ZPR_CURRENCIESDTO_H
#define ZPR_CURRENCIESDTO_H

#include <nlohmann/json.hpp>
#include <string>

#include "CurrencyDTO.h"

using nlohmann::json;

namespace cf {
    struct CurrenciesDTO {
        std::vector<CurrencyDTO> currencies;
    };

    void to_json(json &j, const CurrenciesDTO &obj);

    void from_json(const json &j, CurrenciesDTO &obj);
}

#endif //ZPR_CURRENCIESDTO_H
