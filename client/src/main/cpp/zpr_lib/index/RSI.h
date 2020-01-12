#ifndef ZPR_RSI_H
#define ZPR_RSI_H

#include <memory>
#include <map>
#include "model/DataBuffer.h"
#include "RealtimeIndexCalculator.h"

class RSI : public RealtimeIndexCalculator {
    using PData = std::shared_ptr<Data>;
public:
    RSI(const size_t &windowSize, const PData &defaultValue);

    double calc(const PData &data) final;

private:
    DataBuffer<PData> lossData_;
    DataBuffer<PData> gainData_;
    PData previous_;
    double avgGain_;
    double avgLoss_;
    int windowSize_;
};


#endif //ZPR_RSI_H
