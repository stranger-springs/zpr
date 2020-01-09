#ifndef ZPR_MODELPROXY_H
#define ZPR_MODELPROXY_H

#include <memory>
#include <string>
#include <vector>
#include "ModelController.h"


class ModelProxy {
public:
    static ModelProxy &getInstance(std::string config);

    std::string updateIndex(const std::string &jsonString);

    std::string updateAggregation();

private:
    explicit ModelProxy(const std::string &jsonConfig);

    ModelProxy(const ModelProxy &) = delete;

    ModelProxy &operator=(const ModelProxy &) = delete;

    ModelController modelController_;
};


#endif //ZPR_MODELPROXY_H
