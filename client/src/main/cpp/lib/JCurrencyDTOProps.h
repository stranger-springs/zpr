#ifndef ZPR_JCURRENCYDTOPROPS_H
#define ZPR_JCURRENCYDTOPROPS_H

#include <jni.h>

class JCurrencyDTOProps {
public:
    jclass currencyDTOClass;
    jmethodID getPrice;
    jmethodID getID;
    jmethodID getName;

    JCurrencyDTOProps(JNIEnv *env) {
        currencyDTOClass = env->FindClass("com/strangersprings/zpr/client/service/calc/CurrencyDTO");
        getPrice = env->GetMethodID(currencyDTOClass, "getPrice", "()D");
        getID = env->GetMethodID(currencyDTOClass, "getId", "()J");
        getName = env->GetMethodID(currencyDTOClass, "getName", "()Ljava/lang/String;");
    }
};

#endif //ZPR_JCURRENCYDTOPROPS_H
