#include <config/JsonUtils.h>
#include "ModelUtils.h"

cf::Config ModelUtils::loadConfig(const std::string &jsonConfig) {
    return cf::JsonUtils<cf::Config>::fromJSON(jsonConfig);
}
