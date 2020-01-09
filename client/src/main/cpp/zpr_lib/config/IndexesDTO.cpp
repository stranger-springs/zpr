#include "IndexesDTO.h"

void cf::to_json(json &j, const IndexesDTO &obj) {
    j = json{{"indexes", obj.indexes}};
}

void cf::from_json(const json &j, IndexesDTO &obj) {
    j.at("indexes").get_to(obj.indexes);
}

cf::IndexesDTO::IndexesDTO() {

}

cf::IndexesDTO::IndexesDTO(const std::vector<IndexDTO> &indexes) : indexes(indexes) {

}
