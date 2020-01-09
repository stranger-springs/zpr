#ifndef ZPR_EMA_H
#define ZPR_EMA_H

#include <memory>
#include "model/DataBuffer.h"
#include "RealtimeIndexCalculator.h"
#include "model/Data.h"

class EMA : public RealtimeIndexCalculator {
public:
    EMA(const size_t &windowSize, const std::shared_ptr<Data> &defaultValue);

    double calc(const std::shared_ptr<Data> &data) override;

private:
    double multiplier;
    double previousEma;
};

#endif //ZPR_EMA_H
