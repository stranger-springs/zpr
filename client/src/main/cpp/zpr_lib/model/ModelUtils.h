#ifndef ZPR_MODELUTILS_H
#define ZPR_MODELUTILS_H


#include <config/Config.h>

class ModelUtils {
public:
    static cf::Config loadConfig(const std::string &jsonConfig);
};


#endif //ZPR_MODELUTILS_H
