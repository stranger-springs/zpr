#include "RealtimeIndexFactory.h"
#include "model/DefaultsHolder.h"
#include "EMA.h"
#include "RSI.h"
#include "SMA.h"

RealtimeIndexFactory &RealtimeIndexFactory::getInstance() {
    static RealtimeIndexFactory instance;
    return instance;
}

std::unique_ptr<RealtimeIndexCalculator> RealtimeIndexFactory::create(const IndexType &type, const size_t &windowSize) {
    switch (type) {
        case IndexType::EMA:
            return std::make_unique<EMA>(windowSize, DefaultsHolder::getCurrency());
        case IndexType::SMA:
            return std::make_unique<SMA>(windowSize, DefaultsHolder::getCurrency());
        case IndexType::RSI:
            return std::make_unique<RSI>(windowSize, DefaultsHolder::getCurrency());
        default:
            return std::make_unique<SMA>(windowSize, DefaultsHolder::getCurrency());
    }
}
