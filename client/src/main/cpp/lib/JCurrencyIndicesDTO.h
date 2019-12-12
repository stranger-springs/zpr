#ifndef ZPR_JCURRENCYINDICESDTO_H
#define ZPR_JCURRENCYINDICESDTO_H

#include <string>
#include <jni.h>
#include "JCurrencyIndicesDTOProps.h"

class JCurrencyIndicesDTO {
public:
    JCurrencyIndicesDTO(JNIEnv *env) : env_(env), props_(env) {
        currencyIndicesDTO_ = env_->AllocObject(props_.indicatorClass);
    }

    void setEmaResult(double value) {
        env_->SetDoubleField(currencyIndicesDTO_, props_.emaField, value);
    }

    void setSmaResult(double value) {
        env_->SetDoubleField(currencyIndicesDTO_, props_.smaField, value);
    }

    void setName(std::string name) {
        env_->SetObjectField(currencyIndicesDTO_, props_.nameField, env_->NewStringUTF(name.c_str()));
    }

    jobject getJavaObject() const {
        return currencyIndicesDTO_;
    }

private:
    jobject currencyIndicesDTO_;
    JCurrencyIndicesDTOProps props_;
    JNIEnv *env_;
};

#endif //ZPR_JCURRENCYINDICESDTO_H
