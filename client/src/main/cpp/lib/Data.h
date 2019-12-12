#ifndef ZPR_DATA_H
#define ZPR_DATA_H

class Data {
public:
    virtual double getValue() const = 0;

    virtual long getID() const = 0;

    virtual ~Data() = default;
};


#endif //ZPR_DATA_H
