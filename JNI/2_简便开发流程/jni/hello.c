#include <jni.h>


JNIEXPORT jstring JNICALL Java_com_xfhy_simpleprocess_MainActivity_helloFromC
  (JNIEnv *env, jobject clazz){

	return (*env)->NewStringUTF(env,"Hello from C!");

}
