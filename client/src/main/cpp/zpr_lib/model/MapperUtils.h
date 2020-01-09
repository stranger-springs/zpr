#ifndef ZPR_MAPPERUTILS_H
#define ZPR_MAPPERUTILS_H

#include <vector>
#include <functional>
#include <algorithm>


template<typename T, typename U>
class MapperUtils {
public:
    static std::vector<U> mapVector(const std::vector<T> &input, std::function<U(T)> mapFunc) {
        std::vector<U> result;
        result.reserve(input.size());
        std::transform(input.begin(), input.end(), result.begin(), mapFunc);
        return result;
    }
};


#endif //ZPR_MAPPERUTILS_H
