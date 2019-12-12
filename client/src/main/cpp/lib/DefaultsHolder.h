#ifndef ZPR_DEFAULTSHOLDER_H
#define ZPR_DEFAULTSHOLDER_H

#include <memory>
#include "Currency.h"
#include "Stat.h"

class DefaultsHolder {
public:
    static std::shared_ptr<Currency> getCurrency();
    static std::shared_ptr<Stat> getStat();
};

std::shared_ptr<Currency> DefaultsHolder::getCurrency() {
    static std::shared_ptr<Currency> c = std::make_shared<Currency>();
    return c;
}

std::shared_ptr<Stat> DefaultsHolder::getStat() {
    static std::shared_ptr<Stat> s = std::make_shared<Stat>();
    return s;
}


#endif //ZPR_DEFAULTSHOLDER_H
