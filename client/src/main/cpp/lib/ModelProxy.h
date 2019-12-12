#ifndef ZPR_MODELPROXY_H
#define ZPR_MODELPROXY_H

#include <memory>
#include <string>
#include <vector>
#include "ModelController.h"

using PModelController = std::shared_ptr<ModelController>;
using PIndicesDTO = std::shared_ptr<IndicesDTO>;
using PDataDTO = std::shared_ptr<DataDTO>;

class ModelProxy {
public:
    static ModelProxy& getInstance(std::vector<std::string> names);
    std::map<std::string, PIndicesDTO> calculateAll(const std::vector<PDataDTO> &newData) {
        return controller_->calculateAll(newData);
    }
private:
    ModelProxy(std::vector<std::string> names) : controller_(std::make_shared<ModelController>(names)){}
    ModelProxy(const ModelProxy&) = delete;
    ModelProxy& operator=(const ModelProxy&) = delete;
    PModelController controller_;
};

ModelProxy& ModelProxy::getInstance(std::vector<std::string> names) {
    static ModelProxy instance(names);
    return instance;
}

#endif //ZPR_MODELPROXY_H
