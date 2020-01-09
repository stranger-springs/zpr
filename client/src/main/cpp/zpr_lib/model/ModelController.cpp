#include "ModelController.h"

#include "Mapper.h"
#include "Currency.h"
#include "config/ConfigException.h"
#include "config/CurrenciesDTO.h"
#include "config/JsonUtils.h"

ModelController::ModelController(const cf::Config &config) : names_(config.currencies), indexController_(nullptr),
                                                             aggregationController_(nullptr) {

    try {
        indexController_ = std::make_unique<IndexController>(names_, config.indexConfig);
        aggregationController_ = std::make_unique<AggregationController>(names_, config.aggregationConfig);
    } catch (ConfigException &e) {

    }
}

std::string ModelController::updateIndex(const std::string &jsonString) {
    currenciesToUpdate_ = loadCurrencies(jsonString);
    return Mapper::toJsonString(indexController_->update(currenciesToUpdate_));
}

std::string ModelController::updateAggregation() {
    return Mapper::toJsonString(aggregationController_->update(currenciesToUpdate_));
}

std::map<std::string, std::shared_ptr<Data>> ModelController::loadCurrencies(const std::string &jsonString) const {
    cf::CurrenciesDTO currenciesDto = cf::JsonUtils<cf::CurrenciesDTO>::fromJSON(jsonString);
    auto currencies = Mapper::toCurrenciesMap(currenciesDto);
    std::map<std::string, std::shared_ptr<Data>> currenciesToUpdate;
    for (const auto &c : currencies) {
        if (std::find(names_.begin(), names_.end(), c.first) != names_.end()) {
            currenciesToUpdate.insert(c);
        }
    }
    return currenciesToUpdate;
}
