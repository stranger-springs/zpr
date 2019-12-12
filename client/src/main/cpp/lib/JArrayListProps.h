#ifndef ZPR_JARRAYLISTPROPS_H
#define ZPR_JARRAYLISTPROPS_H

#include <jni.h>

class JArrayListProps {
public:
    jclass java_util_ArrayList;
    jmethodID java_util_ArrayList_init;
    jmethodID java_util_ArrayList_size;
    jmethodID java_util_ArrayList_get;
    jmethodID java_util_ArrayList_add;

    JArrayListProps(JNIEnv *env) {
        java_util_ArrayList = static_cast<jclass>(env->NewGlobalRef(env->FindClass("java/util/ArrayList")));
        java_util_ArrayList_init = env->GetMethodID(java_util_ArrayList, "<init>", "(I)V");
        java_util_ArrayList_size = env->GetMethodID(java_util_ArrayList, "size", "()I");
        java_util_ArrayList_get = env->GetMethodID(java_util_ArrayList, "get", "(I)Ljava/lang/Object;");
        java_util_ArrayList_add = env->GetMethodID(java_util_ArrayList, "add", "(Ljava/lang/Object;)Z");
    }
};

#endif //ZPR_JARRAYLISTPROPS_H
