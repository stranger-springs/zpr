#ifndef ZPR_MODELCONTROLLER_H
#define ZPR_MODELCONTROLLER_H

#include <memory>
#include <string>
#include <vector>
#include "aggregation/AggregationController.h"
#include "config/Config.h"
#include "index/IndexController.h"

class ModelController {
public:
    explicit ModelController(const cf::Config &config);

    std::string updateIndex(const std::string &jsonString);

    std::string updateAggregation();

private:
    std::vector<std::string> names_;
    std::unique_ptr<IndexController> indexController_;
    std::unique_ptr<AggregationController> aggregationController_;
    std::map<std::string, std::shared_ptr<Data>> currenciesToUpdate_;

    std::map<std::string, std::shared_ptr<Data>> loadCurrencies(const std::string &jsonString) const;
};

#endif //ZPR_MODELCONTROLLER_H
