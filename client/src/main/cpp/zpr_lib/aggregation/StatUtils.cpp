#include <algorithm>
#include <numeric>
#include "StatUtils.h"

Stat StatUtils::calcStats(const std::vector<std::shared_ptr<Data>> &buffer) {
    std::pair<double, double> minMax = findMinMax(buffer);
    double average = sumData(buffer) / buffer.size();
    long firstID = buffer[0]->getID();
    long lastID = buffer[buffer.size() - 1]->getID();
    return Stat(lastID, firstID, lastID, average, minMax.first, minMax.second);
}

std::pair<double, double> StatUtils::findMinMax(const std::vector<std::shared_ptr<Data>> &buffer) {

    return std::pair<double, double>();
}

double StatUtils::sumData(const std::vector<std::shared_ptr<Data>> &buffer) {
    std::vector<double> prices;
    prices.resize(buffer.size());
    std::transform(buffer.begin(), buffer.end(), prices.begin(),
                   [](const std::shared_ptr<Data> &a) { return a->getValue(); }
    );
    return std::accumulate(prices.begin(), prices.end(), 0.0);
}
