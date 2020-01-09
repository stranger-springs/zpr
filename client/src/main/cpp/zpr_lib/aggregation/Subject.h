#ifndef ZPR_SUBJECT_H
#define ZPR_SUBJECT_H

#include <vector>
#include <string>
#include "Observer.h"

class Subject {
public:
    void addObserver(Observer *o) { obs_.push_back(o); }

    void notify(const std::string &key) {
        for (Observer *o : obs_) {
            o->update(key);
        }
    }

    virtual ~Subject() = default;

private:
    std::vector<Observer *> obs_;
};


#endif //ZPR_SUBJECT_H
