#include "DefaultsHolder.h"

std::shared_ptr<Currency> DefaultsHolder::getCurrency() {
    static std::shared_ptr<Currency> c = std::make_shared<Currency>();
    return c;
}

std::shared_ptr<Stat> DefaultsHolder::getStat() {
    static std::shared_ptr<Stat> s = std::make_shared<Stat>();
    return s;
}