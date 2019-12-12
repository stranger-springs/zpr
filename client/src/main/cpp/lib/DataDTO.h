#ifndef ZPR_DATADTO_H
#define ZPR_DATADTO_H

#include <string>

class DataDTO {
public:
    std::string name;
    long id;
    double price;

    DataDTO(std::string _name, long _id, double _price) : name(_name), id(_id), price(_price) {}


};

#endif //ZPR_DATADTO_H
