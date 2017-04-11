#include <jni.h>
#include <stdlib.h>
#include <android/log.h>
#define LOG_TAG "xfhy"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

//define是起别名
//LOG_TAG就是tag
//ANDROID_LOG_DEBUG表示优先级     debug      ANDROID_LOG_INFO表示info 这些在log.h中可以看到
//__VA_ARGS__:是可变参数的固定写法


/**
 * 把一个jstring转换成一个c语言的char* 类型.
 */
char* _JString2CStr(JNIEnv* env, jstring jstr) {
	 char* rtn = NULL;
	 jclass clsstring = (*env)->FindClass(env, "java/lang/String");
	 jstring strencode = (*env)->NewStringUTF(env,"GB2312");
	 jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
	 jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid, strencode); // String .getByte("GB2312");
	 jsize alen = (*env)->GetArrayLength(env, barr);
	 jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
	 if(alen > 0) {
		rtn = (char*)malloc(alen+1); //"\0"
		memcpy(rtn, ba, alen);
		rtn[alen]=0;
	 }
	 (*env)->ReleaseByteArrayElements(env, barr, ba,0);
	 return rtn;
}

JNIEXPORT jint JNICALL Java_com_xfhy_javapassdata_JNI_add
  (JNIEnv * env, jobject clazz, jint x, jint y){
	return x+y;   //直接返回x+y
}



/*
 * Class:     com_xfhy_javapassdata_JNI
 * Method:    sayHelloInC
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_xfhy_javapassdata_JNI_sayHelloInC
  (JNIEnv *env, jobject clazz, jstring str){

	//将jstring转换成char* 类型
	char* cstr = _JString2CStr(env,str);
    //调用C语言的strlen测量cstr字符串的长度
	int length = strlen(cstr);
	LOGD("length = %d",length);
	int i=0;
	for(i=0; i<length; i++){
		*(cstr+i) += 1;   //将字符串+1
	}
	return (*env)->NewStringUTF(env,cstr);    //将char* 类型转换成String类型返回

}

/*
 * Class:     com_xfhy_javapassdata_JNI
 * Method:    arrElementsIncrease
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_xfhy_javapassdata_JNI_arrElementsIncrease
  (JNIEnv *env, jobject clazz, jintArray jArray) {
	//jsize       (*GetArrayLength)(JNIEnv*, jarray);    返回数组长度
	int length = (*env)->GetArrayLength(env,jArray);
	//jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);   最后一个参数表示是否拷贝,可以不用传值
	//返回int* 返回该数组的首地址    这样就可以直接通过该指针直接操作该数组了
	int* cArray = (*env)->GetIntArrayElements(env,jArray,NULL);
	int i;
	for(i=0; i<length; i++) {
		*(cArray+i) += 10;
	}
	return jArray;   //直接将原数组返回(这时已经是修改过了的)
}
