#include "com_strangersprings_zpr_client_service_calc_Calculator.h"
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
#include "ModelProxy.h"
#include "JniUtils.h"
#include "JArrayList.h"
#include "JCurrencyDTO.h"
#include "JCurrencyIndicesDTO.h"

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_sayHello
  (JNIEnv *, jobject) {
    std::string hello = "Hello from C++ !!";
    std::cout << hello << std::endl;
}

JNIEXPORT void JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_init
  (JNIEnv *env, jobject callingObject, jobject nameList) {

    JArrayList jNames(env, nameList);
    std::vector<std::string> names;
    names.reserve(jNames.size());

    for (int i = 0; i < jNames.size(); ++i) {
         jstring element = static_cast<jstring>(jNames.get(i));
         const char* pchars = env->GetStringUTFChars(element, nullptr);
         names.emplace_back(pchars);
         env->ReleaseStringUTFChars(element, pchars);
         env->DeleteLocalRef(element);
    }

    ModelProxy &mp = ModelProxy::getInstance(names);
}

JNIEXPORT jobject JNICALL Java_com_strangersprings_zpr_client_service_calc_Calculator_calculateAll
  (JNIEnv *env, jobject callingObject, jobject currencyDTOs){

    JArrayList jCurrencyDTOs(env, currencyDTOs);
    std::vector<std::shared_ptr<DataDTO> > dataDTOs;
    dataDTOs.reserve(jCurrencyDTOs.size());

    for (int i = 0; i < jCurrencyDTOs.size(); ++i) {
        JCurrencyDTO dto(env, jCurrencyDTOs.get(i));
        dataDTOs.emplace_back(JniUtils::mapToDataDTO(env, dto));
    }

    ModelProxy &mp = ModelProxy::getInstance(std::vector<std::string>());
    std::map<std::string, PIndicesDTO> calcResult = mp.calculateAll(dataDTOs);

    JArrayList result(env, calcResult.size());
    for (auto entry : calcResult) {
        result.add(JniUtils::mapToJCurrencyIndicesDTO(env, entry.first, entry.second).getJavaObject());
    }

    return result.getJavaArrayList();
}