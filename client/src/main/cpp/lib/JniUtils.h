#ifndef ZPR_JNIUTILS_H
#define ZPR_JNIUTILS_H

#include <memory>
#include <jni.h>
#include "IndicesDTO.h"
#include "DataDTO.h"
#include "JCurrencyDTO.h"
#include "JCurrencyIndicesDTO.h"

class JniUtils {
public:
    static std::shared_ptr<DataDTO> mapToDataDTO(JNIEnv *env, JCurrencyDTO dto) {
        return std::make_shared<DataDTO>(env->GetStringUTFChars(dto.getName(), NULL), dto.getID(), dto.getPrice());
    }

    static JCurrencyIndicesDTO mapToJCurrencyIndicesDTO(JNIEnv *env, std::string name, std::shared_ptr<IndicesDTO> dto) {
        JCurrencyIndicesDTO currencyIndicesDto(env);
        currencyIndicesDto.setName(name);
        currencyIndicesDto.setEmaResult(dto->emaResult_);
        currencyIndicesDto.setSmaResult(dto->smaResult_);
        return currencyIndicesDto;
    }
};

#endif //ZPR_JNIUTILS_H
