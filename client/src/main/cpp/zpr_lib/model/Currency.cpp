#include "Currency.h"

Currency::Currency() : id_(-1L), price_(0) {}

Currency::Currency(long id, double price) : id_(id), price_(price) {}

double Currency::getValue() const {
    return price_;
}

long Currency::getID() const {
    return id_;
}

