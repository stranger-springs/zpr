#include "IndexController.h"
#include "model/Mapper.h"

#include "model/RealtimeIndex.h"

using PData = std::shared_ptr<Data>;

IndexController::IndexController(const std::vector<std::string> &currencies, const cf::IndexConfig &config)
        : names_(currencies) {
    for (const std::string &id : names_) {
        coordinators_.insert(std::make_pair(id, IndexCoordinator(config)));
    }
}

cf::IndexesDTO IndexController::update(const std::map<std::string, std::shared_ptr<Data>> &newDataMap) {
    std::map<std::string, std::vector<RealtimeIndex>> results;

    for (const auto &pair : newDataMap) {
        auto it = coordinators_.find(pair.first);
        if (it != coordinators_.end()) {
            results.insert(std::make_pair(
                    it->first,
                    it->second.update(pair.second)
            ));
        }
    }
    return Mapper::toIndexesDTO(results);
}
