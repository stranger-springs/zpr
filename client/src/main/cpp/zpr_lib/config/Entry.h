#ifndef ZPR_ENTRY_H
#define ZPR_ENTRY_H

#include <string>
#include <nlohmann/json.hpp>

using nlohmann::json;

namespace cf {
    template<typename T>
    struct Entry {
        std::string key;
        T value;
    };

    template<typename T>
    void to_json(json &j, const Entry<T> &obj) {
        j = json{
                {"key",   obj.key},
                {"value", obj.value}
        };
    }

    template<typename T>
    void from_json(const json &j, Entry<T> &obj) {
        j.at("key").get_to(obj.key);
        j.at("value").get_to(obj.value);
    }

    template<typename T>
    Entry<T> make_entry(const std::string &key, const T &value) {
        Entry<T> entry;
        entry.key = key;
        entry.value = value;
        return entry;
    }
}

#endif //ZPR_ENTRY_H