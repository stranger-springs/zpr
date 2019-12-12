#ifndef ZPR_JCURRENCYDTO_H
#define ZPR_JCURRENCYDTO_H

#include <string>
#include <jni.h>
#include "JCurrencyDTOProps.h"

class JCurrencyDTO {
public:
    JCurrencyDTO(JNIEnv *env, jobject currencyDTO) : env_(env), props_(env), currencyDTO_(currencyDTO) {}

    long getID() const {
        return env_->CallLongMethod(currencyDTO_, props_.getID);
    }

    double getPrice() const {
        return env_->CallDoubleMethod(currencyDTO_, props_.getPrice);
    }

    jstring getName() const {
        return (jstring) env_->CallObjectMethod(currencyDTO_, props_.getName);
    }

private:
    JNIEnv *env_;
    jobject currencyDTO_;
    JCurrencyDTOProps props_;
};

#endif //ZPR_JCURRENCYDTO_H
