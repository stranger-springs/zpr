#ifndef ZPR_INDEXUTILS_H
#define ZPR_INDEXUTILS_H

#include <string>
#include <map>
#include "IndexType.h"

class IndexUtils {
public:
    static IndexUtils &getInstance();

    IndexType getIndexType(std::string name);

private:
    std::map<std::string, IndexType> indexTypeValues_;

    IndexUtils();

    IndexUtils(const IndexUtils &) = delete;

    IndexUtils &operator=(const IndexUtils &) = delete;
};


#endif //ZPR_INDEXUTILS_H
