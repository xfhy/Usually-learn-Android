package com.xfhy.callbackjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 2017年4月8日
 * 
 * xfhy
 * 
 * TODO
 */
public class JNI {

	static {
		System.loadLibrary("call_back");
	}
	
	private Context mContext;
	public JNI(Context context){
		mContext = context;
	}
	public native void callbackvoidmethod();
	
	public native void callbackintmethod();
	
	public native void callbackStringmethod();
	
	public native void callbackShowToast();
	//C调用java空方法
	public void helloFromJava(){
		Log.d("xfhy", "hello from java!");
	}
	//C调用java中的带两个int参数的方法
	public int add(int x,int y) {
		return x+y;
	}
	//C调用java中参数为string的方法
	public void printString(String s){
		System.out.println(s);
	}
	public void showToast(String s){
		Toast.makeText(mContext, s, 0).show();
	}
	
}
