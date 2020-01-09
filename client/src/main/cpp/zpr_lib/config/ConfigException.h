#ifndef ZPR_CONFIGEXCEPTION_H
#define ZPR_CONFIGEXCEPTION_H

#include <exception>
#include <string>

class ConfigException : public std::exception {
public:
    ConfigException(const std::string &message) : message_(message) {}

    std::string getMessage() const { return message_; }

private:
    std::string message_;
};

#endif //ZPR_CONFIGEXCEPTION_H
