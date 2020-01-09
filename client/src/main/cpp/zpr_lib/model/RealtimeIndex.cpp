#include "RealtimeIndex.h"

RealtimeIndex::RealtimeIndex() : id_(-1L), name_(""), value_(0.0) {}

RealtimeIndex::RealtimeIndex(long id, const std::string &name, double value) : id_(id), name_(name), value_(value) {}

double RealtimeIndex::getValue() const {
    return value_;
}

long RealtimeIndex::getID() const {
    return id_;
}

std::string RealtimeIndex::getName() const{
    return name_;
}
