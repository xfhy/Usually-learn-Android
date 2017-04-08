package com.xfhy.javapassdata;

/**
 * 2017年4月8日
 * 
 * xfhy
 * 
 * 定义一些JNI的操作
 */
public class JNI {

	static {
		System.loadLibrary("passdata");   //一定要记得加载动态链接库
	}
	
	//传递2个int值,让C做处理再返回
	public native int add(int x,int y);
	//传递一个字符串过去,让C处理后返回
	public native String sayHelloInC(String s);
	//传递一个数组过去,C处理后返回
	public native int[] arrElementsIncrease(int[] intArray);
	
}
