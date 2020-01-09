#ifndef ZPR_STATUTILS_H
#define ZPR_STATUTILS_H

#include <memory>
#include <model/DataBuffer.h>
#include <model/Currency.h>
#include "model/Stat.h"


class StatUtils {
public:
    static Stat calcStats(const std::vector<std::shared_ptr<Data>> &buffer);

    static double sumData(const std::vector<std::shared_ptr<Data>> &buffer);

private:
    static std::pair<double, double> findMinMax(const std::vector<std::shared_ptr<Data>> &buffer);

};

#endif //ZPR_STATUTILS_H
