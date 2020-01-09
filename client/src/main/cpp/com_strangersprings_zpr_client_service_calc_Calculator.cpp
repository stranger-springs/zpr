#include "com_strangersprings_zpr_client_service_calc_Calculator.h"
#include <iostream>
#include <string>
#include "model/ModelProxy.h"

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_init
  (JNIEnv *env, jobject callingObject, jstring jsonString) {
    std::string config(env->GetStringUTFChars(jsonString, NULL));
    ModelProxy &mp = ModelProxy::getInstance(config);
}

JNIEXPORT jstring JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_updateIndex
  (JNIEnv *env, jobject callingObject, jstring jsonString) {
    std::string config(env->GetStringUTFChars(jsonString, NULL));
    ModelProxy &mp = ModelProxy::getInstance("");
    std::string updateResult = mp.updateIndex(config);
    return env->NewStringUTF(updateResult.c_str());
}

JNIEXPORT jstring JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_updateAggregation
  (JNIEnv *env, jobject callingObject) {
    ModelProxy &mp = ModelProxy::getInstance("");
    std::string updateResult = mp.updateAggregation();
    return env->NewStringUTF(updateResult.c_str());
}
