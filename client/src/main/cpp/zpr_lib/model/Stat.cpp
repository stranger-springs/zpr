#include "Stat.h"

Stat::Stat() : id_(-1L), firstId_(-1L), lastId_(-1L), avg_(0), min_(0), max_(0) {}

Stat::Stat(long id, long first, long last, double avg, double min, double max)
        : id_(id), firstId_(first), lastId_(last), avg_(avg), min_(min), max_(max) {}

double Stat::getValue() const {
    return avg_;
}

long Stat::getID() const {
    return id_;
}

double Stat::getAvg() const {
    return avg_;
}

double Stat::getMin() const {
    return min_;
}

double Stat::getMax() const {
    return max_;
}

long Stat::getFirstId() const {
    return firstId_;
}

long Stat::getLastId() const {
    return lastId_;
}
