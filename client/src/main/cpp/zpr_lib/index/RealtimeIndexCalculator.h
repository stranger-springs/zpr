#ifndef ZPR_REALTIMEINDEXCALCULATOR_H
#define ZPR_REALTIMEINDEXCALCULATOR_H

#include <memory>
#include "model/Data.h"

class RealtimeIndexCalculator {
public:
    virtual double calc(const std::shared_ptr<Data> &data) = 0;

    virtual ~RealtimeIndexCalculator() = default;
};

#endif //ZPR_REALTIMEINDEXCALCULATOR_H
