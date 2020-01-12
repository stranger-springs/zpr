#ifndef ZPR_STAT_H
#define ZPR_STAT_H

#include "model/Data.h"

class Stat : public Data {
public:
    Stat();

    Stat(long id, long first, long last, double avg, double min, double max);

    double getValue() const override;

    long getID() const override;

    double getAvg() const;

    double getMin() const;

    double getMax() const;

    long getFirstId() const;

    long getLastId() const;

private:
    long id_;
    long firstId_;
    long lastId_;
    double avg_;
    double min_;
    double max_;
};


#endif //ZPR_STAT_H
