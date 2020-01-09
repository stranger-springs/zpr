#ifndef ZPR_DATABUFFER_H
#define ZPR_DATABUFFER_H

#include <vector>

template<typename T>
class DataBuffer {
public:
    explicit DataBuffer(size_t size, T defaultVal) : data_(size, defaultVal), it_(size - 1) {}

    T insert(const T &data) {
        size_t insertIndex = getNextItemIndex();
        return insertAtIndex(insertIndex, data);
    }

    std::vector<T> getData() const {
        return data_;
    }

    size_t getBufferSize() const {
        return data_.size();
    }

    T getItemToReplace() const {
        return data_[getNextItemIndex()];
    }

private:
    std::vector<T> data_;
    size_t it_;

    T insertAtIndex(size_t insertIndex, const T &data) {
        data_.at(insertIndex) = data;
        if (++it_ == getBufferSize()) it_ = 0;
        return data_[insertIndex];
    }

    size_t getNextItemIndex() const {
        size_t next = it_ + 1;
        if (next == data_.size()) next = 0;
        return next;
    }
};


#endif //ZPR_DATABUFFER_H
