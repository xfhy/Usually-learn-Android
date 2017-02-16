package com.itheima.muchdown_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 2017年1月23日
 * 
 * XFHY
 * 
 * SharedPreferences的操作类
 */
public class SharedUtils {

	/**
	 * 获取上一次下载到哪个位置
	 * @param context
	 * @param threadId
	 * @return
	 */
	public static int getLastPosition(Context context,int threadId){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		//把key设置成这样是因为    为了区分不同的线程     下一次获取的时候会从这个键值对中获取
		return sharedPreferences.getInt("lastPosition"+threadId, -1);
	}
	
	public static void setLastPosition(Context context,int threadId,int position){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPreferences.edit().putInt("lastPosition"+threadId, position);
	}
	
}
