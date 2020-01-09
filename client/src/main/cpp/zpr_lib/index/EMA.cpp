#include "EMA.h"
#include "aggregation/StatUtils.h"

EMA::EMA(const size_t &windowSize, const std::shared_ptr<Data> &defaultValue) {
    DataBuffer<std::shared_ptr<Data>> buffer(windowSize, defaultValue);
    previousEma = StatUtils::sumData(buffer.getData()) / windowSize; // simple average
    multiplier = 2 / static_cast<double>(windowSize + 1);
}

double EMA::calc(const std::shared_ptr<Data> &data) {
    previousEma = (data->getValue() - previousEma) * multiplier + previousEma;
    return previousEma;
}