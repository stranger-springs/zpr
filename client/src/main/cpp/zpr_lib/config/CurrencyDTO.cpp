#include "CurrencyDTO.h"

void cf::to_json(json &j, const CurrencyDTO &obj) {
    j = json{
            {"id",    obj.id},
            {"name",  obj.name},
            {"value", obj.value},
    };
}

void cf::from_json(const json &j, cf::CurrencyDTO &obj) {
    j.at("id").get_to(obj.id);
    j.at("name").get_to(obj.name);
    j.at("value").get_to(obj.value);
}

cf::CurrencyDTO::CurrencyDTO() = default;

cf::CurrencyDTO::CurrencyDTO(long id, const std::string &name, double value) :
        id(id), name(name), value(value) {

}
