#ifndef ZPR_INDICESDTO_H
#define ZPR_INDICESDTO_H

#include <memory>
#include "Data.h"

class IndicesDTO {
public:
    IndicesDTO(const std::shared_ptr<Data>& data, double smaResult, double emaResult): data_(data), smaResult_(smaResult), emaResult_(emaResult) {}
    double smaResult_;
    double emaResult_;
    PData data_;
};

#endif //ZPR_INDICESDTO_H
