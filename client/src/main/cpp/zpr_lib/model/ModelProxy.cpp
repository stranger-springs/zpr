#include "ModelProxy.h"
#include "config/JsonUtils.h"
#include "ModelUtils.h"

ModelProxy &ModelProxy::getInstance(std::string config) {
    static ModelProxy instance(config);
    return instance;
}

ModelProxy::ModelProxy(const std::string &jsonConfig) : modelController_(ModelUtils::loadConfig(jsonConfig)) {

}

std::string ModelProxy::updateIndex(const std::string &jsonString) {
    return modelController_.updateIndex(jsonString);
}

std::string ModelProxy::updateAggregation() {
    return modelController_.updateAggregation();
}


