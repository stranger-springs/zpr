#ifndef ZPR_REALTIMEINDEXFACTORY_H
#define ZPR_REALTIMEINDEXFACTORY_H

#include <memory>
#include "index/RealtimeIndexCalculator.h"
#include "index/IndexType.h"

class RealtimeIndexFactory {
public:
    static RealtimeIndexFactory& getInstance();
    std::unique_ptr<RealtimeIndexCalculator> create(const IndexType &type, const size_t &windowSize);

private:
    RealtimeIndexFactory() = default;
    RealtimeIndexFactory(const RealtimeIndexFactory&) = delete;
    RealtimeIndexFactory& operator=(const RealtimeIndexFactory&) = delete;

};

#endif //ZPR_REALTIMEINDEXFACTORY_H
