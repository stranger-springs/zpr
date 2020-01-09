#include "AggregationConfigValidator.h"
#include "config/ConfigException.h"

void AggregationConfigValidator::validate(const cf::AggregationConfig &config) {
    std::vector<std::string> ids = config.ids;
    if (ids.size() != config.types.size()) {
        throw ConfigException("The number of ids and types is not equals");
    }
    auto uniqueIt = std::unique(ids.begin(), ids.end());
    if (uniqueIt != ids.end()) {
        throw ConfigException("Aggregation config ids are not unique");
    }
    for (const auto &type : config.types) {
        auto id = std::find(ids.begin(), ids.end(), type.id);
        if (id == ids.end()) {
            throw ConfigException("Aggregation config id: " + type.id + " not belongs to ids list");
        }

        if (!type.next.empty()) {
            auto next = std::find(ids.begin(), ids.end(), type.next);
            if (next == ids.end()) {
                throw ConfigException("Aggregation config next: " + type.next + " not belongs to ids list");
            }
        }
    }
}
