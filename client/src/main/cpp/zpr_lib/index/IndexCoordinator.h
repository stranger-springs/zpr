#ifndef ZPR_INDEXCOORDINATOR_H
#define ZPR_INDEXCOORDINATOR_H


#include "model/RealtimeIndex.h"
#include "config/IndexConfig.h"
#include "RealtimeIndexCalculator.h"

class IndexCoordinator {
public:
    explicit IndexCoordinator(const cf::IndexConfig &config);

    std::vector<RealtimeIndex> update(const std::shared_ptr<Data> &newData);

private:
    std::unordered_map<std::string, std::unique_ptr<RealtimeIndexCalculator>> indexes_;
};


#endif //ZPR_INDEXCOORDINATOR_H
