#ifndef ZPR_CURRENCYDTO_H
#define ZPR_CURRENCYDTO_H

#include <nlohmann/json.hpp>
#include <string>

using nlohmann::json;

namespace cf {
    struct CurrencyDTO {
        long id;
        std::string name;
        double value;

        CurrencyDTO();

        CurrencyDTO(long id, const std::string &name, double value);
    };

    void to_json(json &j, const CurrencyDTO &obj);

    void from_json(const json &j, CurrencyDTO &obj);
}

#endif //ZPR_CURRENCYDTO_H
