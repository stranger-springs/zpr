#include "AggregationCoordinator.h"
#include "model/DefaultsHolder.h"
#include "StatUtils.h"

AggregationCoordinator::AggregationCoordinator(const cf::AggregationConfig &config) : first_(config.firstId) {

    std::vector<cf::Aggregation> types = config.types;
    for (const auto &t : types) {
        auto buffer = createBuffer(t.value, t.id, DefaultsHolder::getStat());
        buffers_.insert(std::make_pair(t.id, buffer));
        successors_.insert(std::make_pair(t.id, t.next));
    }
}

void AggregationCoordinator::update(const std::string &name) {
    Stat stats = StatUtils::calcStats(buffers_[name]->getBuffer());
    updateResult_.insert(std::make_pair(name, stats));
    if (!successors_[name].empty()) {
        buffers_[successors_[name]]->insert(std::make_shared<Stat>(stats));
    }
}

std::map<std::string, Stat> AggregationCoordinator::update(const std::shared_ptr<Data> &data) {
    updateResult_.clear();
    buffers_[first_]->insert(data);
    return updateResult_;
}

AggregationCoordinator::PBuffer AggregationCoordinator::createBuffer(size_t size, const std::string &key, const std::shared_ptr<Data> &defVal) const {
    return std::make_shared<AggregatingBuffer<std::shared_ptr<Data>>>(size, key, defVal);
}

void AggregationCoordinator::onInit() {
    for (auto& pair : buffers_){
        pair.second->addObserver(this);
    }
}
