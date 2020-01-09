#include "IndexCoordinator.h"

#include "model/Currency.h"
#include "model/RealtimeIndex.h"
#include "IndexUtils.h"
#include "RealtimeIndexFactory.h"

IndexCoordinator::IndexCoordinator(const cf::IndexConfig &config) {

    RealtimeIndexFactory &indexFactory = RealtimeIndexFactory::getInstance();
    IndexUtils &indexUtils = IndexUtils::getInstance();

    for (const auto &index : config.indexes) {
        IndexType indexType = indexUtils.getIndexType(index.type);

        for (const auto &entry : index.entries) {
            indexes_.insert(std::make_pair(entry.key, indexFactory.create(indexType, entry.value)));
        }
    }
}

std::vector<RealtimeIndex> IndexCoordinator::update(const std::shared_ptr<Data> &newData) {
    std::vector<RealtimeIndex> results;
    results.reserve(indexes_.size());
    for (const auto &index : indexes_) {
        results.emplace_back(newData->getID(), index.first, index.second->calc(newData));
    }
    return results;
}
