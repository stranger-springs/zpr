#include <model/Mapper.h>
#include "AggregationController.h"
#include "config/ConfigException.h"
#include "AggregationConfigValidator.h"

AggregationController::AggregationController(const std::vector<std::string> &currencies,
                                             const cf::AggregationConfig &config) : names_(currencies) {
    AggregationConfigValidator::validate(config);
    for (const std::string &id : names_) {
        AggregationCoordinator coordinator(config);
        auto pair = std::make_pair(id, coordinator);
        coordinators_.insert(pair);
    }

    for (auto& pair : coordinators_){
        pair.second.onInit();
    }
}

cf::AggregationDTO AggregationController::update(const std::map<std::string, std::shared_ptr<Data>> &input) {
    std::map<std::string, std::map<std::string,Stat>> results;
    for (auto &pair : input) {
        auto it = coordinators_.find(pair.first);
        if (it != coordinators_.end()) {
            results.insert(std::make_pair(it->first, it->second.update(pair.second)));
        }
    }
    return Mapper::toAggregationDTO(results);
}
