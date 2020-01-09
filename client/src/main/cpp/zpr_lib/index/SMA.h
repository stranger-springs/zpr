#ifndef ZPR_SMA_H
#define ZPR_SMA_H

#include "model/DataBuffer.h"
#include "model/Data.h"
#include "RealtimeIndexCalculator.h"
#include <memory>

using PData = std::shared_ptr<Data>;

class SMA : public RealtimeIndexCalculator {
public:
    SMA(const size_t &windowSize, const PData &defaultValue);

    double calc(const PData &data) final;

private:
    DataBuffer<PData> buffer_;
    double sum;
};

#endif //ZPR_SMA_H
