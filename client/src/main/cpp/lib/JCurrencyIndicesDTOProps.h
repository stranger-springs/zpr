#ifndef ZPR_JCURRENCYINDICESDTOPROPS_H
#define ZPR_JCURRENCYINDICESDTOPROPS_H

#include <jni.h>

class JCurrencyIndicesDTOProps {
public:
    jclass indicatorClass;
    jfieldID nameField;
    jfieldID emaField;
    jfieldID smaField;

    JCurrencyIndicesDTOProps(JNIEnv *env) {
        indicatorClass = env->FindClass("com/strangersprings/zpr/client/service/calc/CurrencyIndicesDTO");
        nameField = env->GetFieldID(indicatorClass, "name", "Ljava/lang/String;");
        emaField = env->GetFieldID(indicatorClass, "smaResult", "D");
        smaField = env->GetFieldID(indicatorClass, "emaResult", "D");
    }


};

#endif //ZPR_JCURRENCYINDICESDTOPROPS_H
