#ifndef ZPR_OBSERVER_H
#define ZPR_OBSERVER_H

class Observer {
public:
    virtual void update(const std::string &name) = 0;

    virtual ~Observer() = default;
};

#endif //ZPR_OBSERVER_H
