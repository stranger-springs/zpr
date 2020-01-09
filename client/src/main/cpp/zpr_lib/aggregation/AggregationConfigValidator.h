#ifndef ZPR_AGGREGATIONCONFIGVALIDATOR_H
#define ZPR_AGGREGATIONCONFIGVALIDATOR_H

#include <config/AggregationConfig.h>

class AggregationConfigValidator {
public:
    static void validate(const cf::AggregationConfig &config);
};


#endif //ZPR_AGGREGATIONCONFIGVALIDATOR_H
