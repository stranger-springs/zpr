#ifndef ZPR_JARRAYLIST_H
#define ZPR_JARRAYLIST_H

#include <jni.h>
#include "JArrayListProps.h"

class JArrayList {
public:
    JArrayList(JNIEnv *env, int size) : env_(env), props_(env){
        arrayList_ = env->NewObject(props_.java_util_ArrayList, props_.java_util_ArrayList_init, size);
    }

    JArrayList(JNIEnv *env, jobject arrayList) : env_(env), props_(env), arrayList_(arrayList) {

    }

    int size() const {
        return env_->CallIntMethod(arrayList_, props_.java_util_ArrayList_size);
    }

    jobject get(int i) const {
        return env_->CallObjectMethod(arrayList_, props_.java_util_ArrayList_get, i);
    }

    bool add(jobject newObject) {
        return env_->CallBooleanMethod(arrayList_, props_.java_util_ArrayList_add, newObject);
    }

    jobject getJavaArrayList() const{
        return arrayList_;
    }

private:
    jobject arrayList_;
    JArrayListProps props_;
    JNIEnv *env_;
};

#endif //ZPR_JARRAYLIST_H
