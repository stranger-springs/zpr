#ifndef ZPR_CALCCOORDINATOR_H
#define ZPR_CALCCOORDINATOR_H

#include <memory>
#include "Data.h"
#include "Currency.h"
#include "SMA.h"
#include "IndicesDTO.h"
#include "EMA.h"

using PData = std::shared_ptr<Data>;
using PCurrency = std::shared_ptr<Currency>;
using PIndicesDTO = std::shared_ptr<IndicesDTO>;

class CalcCoordinator {
public:
    CalcCoordinator(size_t inputSize, size_t smaSize, size_t emaSize, const PData &defaultValue) :
            inputBuffer_(inputSize, defaultValue), sma_(smaSize, defaultValue), ema_(emaSize, defaultValue) {}

    PIndicesDTO calcIndices(long id, double price) {
        PData newValue = inputBuffer_.insert(createCurrency(id, price));
        return std::make_shared<IndicesDTO>(newValue, sma_.calc(newValue), ema_.calc(newValue));
    }

private:
    DataBuffer<PData> inputBuffer_;
    SMA sma_;
    EMA ema_;

    static PCurrency createCurrency(long id, double price) {
        return std::make_shared<Currency>(id, price);
    }
};

#endif //ZPR_CALCCOORDINATOR_H
