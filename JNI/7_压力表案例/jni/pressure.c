#include <jni.h>
#include<stdlib.h>

int getPressure() {
	return rand() % 100;
}

int flag = 0;

/*
 * Class:     com_xfhy_pressuredemo_MainActivity
 * Method:    startPressure   开始检测    死循环一直回调java的方法,更新UI
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_xfhy_pressuredemo_MainActivity_startPressure(
		JNIEnv *env, jobject obj) {
	flag = 1;
	while (flag) {   //死循环

		sleep(1);   //睡一秒

		//1.找到字节码对象
		//jclass      (*FindClass)(JNIEnv*, const char*);
		//参数: env,需要反射的对象的全路径
		jclass mainClass = (*env)->FindClass(env,
				"com/xfhy/pressuredemo/MainActivity");
		//2.通过字节码对象找到方法对象
		//jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
		//第二个参数 字节码对象 第三个参数 要反射调用的java方法名 第四个参数 要反射调用的java方法签名
		//javap -s 要获取方法签名的类的全类名 项目/bin/classes 运行javap
		jmethodID methodID = (*env)->GetMethodID(env, mainClass,
				"setMyProgress", "(I)V");
		//4.反射调用java方法
		//void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
		//第二个参数 调用java方法的对象 第三个参数 要调用的jmethodID对象 可选的参数 调用方法时接收的参数
		(*env)->CallVoidMethod(env, obj, methodID, getPressure());
	}

}

/*
 * Class:     com_xfhy_pressuredemo_MainActivity
 * Method:    stopPressure   停止检测
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_xfhy_pressuredemo_MainActivity_stopPressure(
		JNIEnv *env, jobject obj) {
	flag = 0;
}
