#include <jni.h>
#include <stdlib.h>


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
  (JNIEnv *, jobject, jintArray);
