#ifndef ZPR_INDEXCONTROLLER_H
#define ZPR_INDEXCONTROLLER_H


#include <config/IndexesDTO.h>
#include "config/IndexConfig.h"
#include "IndexCoordinator.h"

class IndexController {
public:
    IndexController(const std::vector<std::string> &currencies, const cf::IndexConfig &config);

    cf::IndexesDTO update(const std::map<std::string, std::shared_ptr<Data>> &newDataMap);

private:
    std::vector<std::string> names_;
    std::unordered_map<std::string, IndexCoordinator> coordinators_;
};


#endif //ZPR_INDEXCONTROLLER_H
