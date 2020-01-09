#ifndef ZPR_JSONUTILS_H
#define ZPR_JSONUTILS_H

#include <nlohmann/json.hpp>

using nlohmann::json;

namespace cf {
    template<typename T>
    class JsonUtils {
    public:
        static T fromJSON(const std::string &jsonString) {
            json j = json::parse(jsonString);
            T res = j;
            return res;
        }

        static std::string toString(const T &object) {
            json j = object;
            return j.dump();
        }
    };
}

#endif //ZPR_JSONUTILS_H
