#include <jni.h>
#include "com_xfhy_cppjni_MainActivity.h"   //C++中的函数记得声明,然后需要include

JNIEXPORT jstring JNICALL Java_com_xfhy_cppjni_MainActivity_helloFromCpp
  (JNIEnv *env, jobject obj){
	return env->NewStringUTF("哈哈Hello from C++!");
}
