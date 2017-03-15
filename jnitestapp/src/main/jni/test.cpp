//
// Created by 李亮 on 2017/2/17.
//

#include <jni.h>
#include <stdio.h>

#ifdef  __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_liang_jnitestapp_MainActivity_get(JNIEnv *env, jobject thiz) {
    printf("invoke get in c++\n");
    return env->NewStringUTF("Hello from JNI in libjni-test.so");
}

JNIEXPORT void JNICALL Java_com_liang_jnitestapp_MainActivity_set
        (JNIEnv *env, jobject thiz, jstring string) {
    printf("invoke set from C++\n");
    char *str = (char *)env->GetStringUTFChars(string, NULL);
    printf("%s\n", str);
    env->ReleaseStringUTFChars(string, str);
}

#ifdef __cplusplus
}
#endif
