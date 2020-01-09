#ifndef ZPR_RSI_H
#define ZPR_RSI_H

#include <memory>
#include "RealtimeIndexCalculator.h"

using PData = std::shared_ptr<Data>;

class RSI : public RealtimeIndexCalculator {
public:
    RSI(const size_t &windowSize, const PData &defaultValue);
    double calc(const PData &data) final;
};


#endif //ZPR_RSI_H
