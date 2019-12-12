#ifndef ZPR_STAT_H
#define ZPR_STAT_H

#include "Data.h"

class Stat : public Data {
public:
    Stat() : id_(-1L), firstId_(-1L), lastId_(-1L), avg_(0), min_(0), max_(0) {}

    Stat(long id, long first, long last, double avg, double min, double max)
            : id_(id), firstId_(first), lastId_(last), avg_(avg), min_(min), max_(max) {}

    virtual double getValue() const {
        return avg_;
    }

    virtual long getID() const {
        return id_;
    }

    long getFirstId() const {
        return firstId_;
    }

    long getLastId() const {
        return lastId_;
    }

    double getAvg() const {
        return avg_;
    }

    double getMin() const {
        return min_;
    }

    double getMax() const {
        return max_;
    }


private:
    long id_;
    long firstId_;
    long lastId_;
    double avg_;
    double min_;
    double max_;
};


#endif //ZPR_STAT_H
