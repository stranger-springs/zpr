#ifndef ZPR_REALTIMEINDEX_H
#define ZPR_REALTIMEINDEX_H

#include <string>
#include "Data.h"

class RealtimeIndex : public Data {
public:
    RealtimeIndex();

    RealtimeIndex(long id, const std::string &name, double value);

    double getValue() const override;

    long getID() const override;

    std::string getName() const;

private:
    long id_;
    double value_;
    std::string name_;
};


#endif //ZPR_REALTIMEINDEX_H
