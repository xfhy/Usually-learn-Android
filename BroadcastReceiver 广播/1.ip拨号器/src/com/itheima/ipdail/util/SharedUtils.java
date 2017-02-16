package com.itheima.ipdail.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * 2017年1月25日
 * 
 * XFHY
 * 
 * 操作SharedPreferences
 */
public class SharedUtils {
	
	private final static String FILE_NAME = "config";
	private final static String IP_NUMBER_KEY = "ipnumber";
	
	public static void saveNumber(Context context,String number){
		SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		boolean isSuccess = sharedPreferences.edit().putString(IP_NUMBER_KEY, number).commit();
		if (isSuccess) {
			Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
		}
	}
	
	public static String getNumber(Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(IP_NUMBER_KEY, "");
	}
	
}
