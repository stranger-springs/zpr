#include "com_strangersprings_zpr_client_service_calc_Calculator.h"
#include <iostream>
#include <vector>

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_sayHello
  (JNIEnv *, jobject) {
    std::string hello = "Hello from C++ !!";
    std::cout << hello << std::endl;
}

JNIEXPORT jobject JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_calculate
  (JNIEnv *env, jobject callingObject, jobject currencyDTO) {
    jclass currencyClass = env->GetObjectClass(currencyDTO);
    jclass indicatorClass = env->FindClass("com/strangersprings/zpr/client/service/calc/CurrencyIndicator");

    jmethodID midgetPrice = env->GetMethodID(currencyClass, "getPrice","()D");
    double price = env->CallDoubleMethod(currencyDTO, midgetPrice);
    std::cout << "Current price: " << price << std::endl;

    price += 2;


    std::cout << "Next price: " << price << std::endl;
    jobject newIndicator = env->AllocObject(indicatorClass);
    jfieldID valueField = env->GetFieldID(indicatorClass , "value", "D");

    env->SetDoubleField(newIndicator, valueField, price);

    return newIndicator;
}