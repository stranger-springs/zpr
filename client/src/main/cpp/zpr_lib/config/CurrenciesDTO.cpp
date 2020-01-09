#include "CurrenciesDTO.h"

void cf::to_json(json &j, const CurrenciesDTO &obj) {
    j = json{{"currencies", obj.currencies}};
}

void cf::from_json(const json &j, CurrenciesDTO &obj) {
    j.at("currencies").get_to(obj.currencies);
}
