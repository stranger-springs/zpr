#include "AggregationDTO.h"

void cf::to_json(json &j, const AggregationDTO &obj) {
    j = json{{"result", obj.result}};
}

void cf::from_json(const json &j, AggregationDTO &obj) {
    j.at("result").get_to(obj.result);
}

cf::AggregationDTO::AggregationDTO(std::vector<cf::Entry<cf::StatDTO>> entries) : result(entries) {

}
