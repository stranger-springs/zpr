#ifndef ZPR_CURRENCY_H
#define ZPR_CURRENCY_H

#include "Data.h"

class Currency : public Data {
public:
    Currency();

    Currency(long id, double price);

    double getValue() const override;

    long getID() const override;

private:
    long id_;
    double price_;
};


#endif //ZPR_CURRENCY_H
