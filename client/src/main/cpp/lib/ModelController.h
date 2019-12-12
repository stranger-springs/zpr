#ifndef ZPR_MODELCONTROLLER_H
#define ZPR_MODELCONTROLLER_H

#include <string>
#include <vector>
#include <map>
#include "CalcCoordinator.h"
#include "DefaultsHolder.h"
#include "DataDTO.h"
#include "ModelProxy.h"

using PCalcCoordinator = std::shared_ptr<CalcCoordinator>;
using PIndicesDTO = std::shared_ptr<IndicesDTO>;
using PDataDTO = std::shared_ptr<DataDTO>;

class ModelController {
public:
    ModelController(const std::vector<std::string> &names) : names_(names) {
        for (const std::string &name : names) {
            coordinators_.insert(
                    std::make_pair(name, std::make_shared<CalcCoordinator>(20, 4, 4, DefaultsHolder::getCurrency())));
        }
    }

    std::map<std::string, PIndicesDTO> calculateAll(const std::vector<PDataDTO> &newData) {
        std::map<std::string, PIndicesDTO> results;
        for (const PDataDTO &dto : newData) {
            auto entry = coordinators_.find(dto->name);
            if (!(entry == coordinators_.end())) {
                PIndicesDTO indicesDto = entry->second->calcIndices(dto->id, dto->price);
                results.insert(std::make_pair(dto->name, indicesDto));
            }
        }
        return results;
    }

private:
    std::vector<std::string> names_;
    std::map<std::string, PCalcCoordinator> coordinators_;
};

#endif //ZPR_MODELCONTROLLER_H
