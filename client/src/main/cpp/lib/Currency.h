#ifndef ZPR_CURRENCY_H
#define ZPR_CURRENCY_H

#include "Data.h"

class Currency : public Data {
public:
    Currency() : id_(-1L), price_(0) {}

    Currency(long id, double price) : id_(id), price_(price) {}

    virtual double getValue() const {
        return price_;
    }

    virtual long getID() const {
        return id_;
    }

    long getId() const {
        return id_;
    }

    double getPrice() const {
        return price_;
    }

private:
    long id_;
    double price_;
};


#endif //ZPR_CURRENCY_H
