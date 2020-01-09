#ifndef ZPR_DEFAULTSHOLDER_H
#define ZPR_DEFAULTSHOLDER_H

#include <memory>
#include "model/Currency.h"
#include "model/Stat.h"

class DefaultsHolder {
public:
    static std::shared_ptr<Currency> getCurrency();

    static std::shared_ptr<Stat> getStat();
};


#endif //ZPR_DEFAULTSHOLDER_H
