#ifndef ZPR_SMA_H
#define ZPR_SMA_H

#include "DataBuffer.h"
#include "Data.h"
#include "Utils.h"
#include <memory>

using PData = std::shared_ptr<Data>;

class SMA {
public:
    SMA(const size_t &windowSize, const PData &defaultValue) : buffer_(windowSize, defaultValue) {
        sum = Utils::getSum(buffer_.getData());
    }

    double calc(const PData &newData) {
        PData elementToRemove = buffer_.getItemToReplace();
        sum += newData->getValue() - elementToRemove->getValue();
        buffer_.insert(newData);
        return sum / buffer_.getBufferSize();
    }

private:
    DataBuffer<PData> buffer_;
    double sum;
};

#endif //ZPR_SMA_H
