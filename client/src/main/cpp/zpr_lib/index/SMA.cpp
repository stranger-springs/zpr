#include <aggregation/StatUtils.h>
#include "SMA.h"

SMA::SMA(const size_t &windowSize, const PData &defaultValue) : buffer_(windowSize, defaultValue) {
    sum = StatUtils::sumData(buffer_.getData());
}

double SMA::calc(const PData &data) {
    PData elementToRemove = buffer_.getItemToReplace();
    sum += data->getValue() - elementToRemove->getValue();
    buffer_.insert(data);
    return sum / buffer_.getBufferSize();
}
