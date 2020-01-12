#include "RSI.h"
#include "model/DefaultsHolder.h"

RSI::RSI(const size_t &windowSize, const PData &defaultValue) : lossData_(windowSize, defaultValue),
                                                                gainData_(windowSize, defaultValue),
                                                                previous_(DefaultsHolder::getCurrency()),
                                                                avgLoss_(0), avgGain_(0), windowSize_(windowSize) {

}

double RSI::calc(const PData &data) {
    double diff = data->getValue() - previous_->getValue();
    if (diff >= 0) {
        gainData_.insert(data);
        lossData_.insert(DefaultsHolder::getCurrency());
        avgGain_ = (avgGain_ * (windowSize_ - 1) + diff) / static_cast<double>(windowSize_);
        avgLoss_ = (avgGain_ * (windowSize_ - 1) + 0) / static_cast<double>(windowSize_);
    } else {
        gainData_.insert(DefaultsHolder::getCurrency());
        lossData_.insert(data);
        avgGain_ = (avgGain_ * (windowSize_ - 1) + 0) / static_cast<double>(windowSize_);
        avgLoss_ = (avgGain_ * (windowSize_ - 1) + (-diff)) / static_cast<double>(windowSize_);
    }
    previous_ = data;
    return (100.0 - (100.0 /( 1.0 + (avgGain_ / avgLoss_ != 0 ? avgLoss_ : 0.000001))));
}
