#ifndef ZPR_AGGREGATINGBUFFER_H
#define ZPR_AGGREGATINGBUFFER_H

#include <memory>
#include "model/Data.h"
#include "model/DataBuffer.h"
#include "Subject.h"

template<typename T>
class AggregatingBuffer : public Subject {
public:
    AggregatingBuffer(size_t size, const std::string &key, const T &defVal) :
            buffer_(size, defVal), SIZE_(size), KEY_(key), it_(0) {

    }

    void insert(const T &newData) {
        buffer_.insert(newData);
        if (++it_ == SIZE_) {
            Subject::notify(KEY_);
            it_ = 0;
        }
    }

    std::vector<T> getBuffer() const {
        return buffer_.getData();
    }

private:
    const std::string KEY_;
    const size_t SIZE_;
    DataBuffer<T> buffer_;
    size_t it_;
};


#endif //ZPR_AGGREGATINGBUFFER_H
