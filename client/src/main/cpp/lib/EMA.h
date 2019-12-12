#ifndef ZPR_EMA_H
#define ZPR_EMA_H

#include <memory>
#include "DataBuffer.h"
#include "Data.h"
#include "Utils.h"

using PData = std::shared_ptr<Data>;

class EMA {
public:
    EMA(const size_t &windowSize, const PData &defaultValue) {
        DataBuffer<PData> buffer(windowSize, defaultValue);
        previousEma = Utils::getSum(buffer.getData()) / windowSize; // simple average
        multiplier = 2 / static_cast<double>(windowSize + 1);
    }

    double calc(const PData &newData) {
        previousEma = (newData->getValue() - previousEma) * multiplier + previousEma;
        return previousEma;
    }

private:
    double multiplier;
    double previousEma;
};

#endif //ZPR_EMA_H
