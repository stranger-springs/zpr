#include "IndexUtils.h"

IndexType IndexUtils::getIndexType(std::string name) {
    return indexTypeValues_[name];
}

IndexUtils &IndexUtils::getInstance() {
    static IndexUtils instance;
    return instance;
}

IndexUtils::IndexUtils() {
    indexTypeValues_.insert(std::make_pair("ema", IndexType::EMA));
    indexTypeValues_.insert(std::make_pair("rsi", IndexType::RSI));
    indexTypeValues_.insert(std::make_pair("sma", IndexType::SMA));
}
