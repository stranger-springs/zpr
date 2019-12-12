#ifndef ZPR_UTILS_H
#define ZPR_UTILS_H

#include <vector>
#include <memory>
#include <algorithm>
#include <numeric>
#include <functional>
#include "Data.h"

using PData = std::shared_ptr<Data>;

class Utils {
public:
    static double getSum(const std::vector<PData> &data);

    static std::function<double(const PData &a)> getMappingFunc();

    static std::function<bool(const PData &a, const PData &b)> getCompareFunc();
};

double Utils::getSum(const std::vector<PData> &data) {
    std::vector<double> prices;
    prices.resize(data.size());
    std::transform(data.begin(), data.end(), prices.begin(), getMappingFunc());
    return std::accumulate(prices.begin(), prices.end(), 0.0);
}

std::function<double(const PData &a)> Utils::getMappingFunc() {
    return [](const PData &a) { return a->getValue(); };
}

std::function<bool(const PData &a, const PData &b)> Utils::getCompareFunc() {
    return [](const PData &a, const PData &b) { return a->getValue() < b->getValue(); };
}

#endif //ZPR_UTILS_H
