//
// Created by mateusz on 11/22/19.
//

#ifndef ZPR_DATAHOLDER_H
#define ZPR_DATAHOLDER_H

#include <vector>

template <class T>
class DataHolder {
public:
    explicit DataHolder(size_t size) : data_(size), it_(0) {}

    void insert(const T& data) {
        data_.at(getInsertIndex()) = data;
    }

    std::vector<T> getData() const {
        return data_;
    }
private:
    std::vector<T> data_;
    size_t it_;

    size_t getInsertIndex() {
        if (++it_ == data_.size()) it_ = 0;
        return it_;
    }
};



#endif //ZPR_DATAHOLDER_H
