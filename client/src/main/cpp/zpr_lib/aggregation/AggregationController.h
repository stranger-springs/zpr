#ifndef ZPR_AGGREGATIONCONTROLLER_H
#define ZPR_AGGREGATIONCONTROLLER_H

#include <config/AggregationDTO.h>
#include "config/AggregationConfig.h"
#include "AggregationCoordinator.h"

class AggregationController {
public:
    explicit AggregationController(const std::vector<std::string> &currencies, const cf::AggregationConfig &config);

    cf::AggregationDTO update(const std::map<std::string, std::shared_ptr<Data>> &input);

private:
    std::vector<std::string> names_;
    std::unordered_map<std::string, AggregationCoordinator> coordinators_;
};


#endif //ZPR_AGGREGATIONCONTROLLER_H
