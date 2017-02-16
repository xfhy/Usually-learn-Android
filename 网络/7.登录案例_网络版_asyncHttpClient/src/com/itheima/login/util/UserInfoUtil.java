package com.itheima.login.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 2017年1月14日16:59:52
 * 
 * @author XFHY 工具类
 */
public class UserInfoUtil {

	 public static final String PREFS_NAME = "MyPrefsFile";

	
	// 保存用户密码
	public static boolean saveUserInfo(Context context,String username, String userpassword) {
		
		SharedPreferences userinfoPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		Editor editor = userinfoPreferences.edit();
		editor.putString("username", username);
		editor.putString("password", userpassword);
		if(editor.commit()){
			return true;
		} else {
			return false;
		}
		
	}

	// 读取用户名和密码
	public static Map<String, String> readUserInfo(Context context) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		String username = sharedPreferences.getString("username", "");
		String password = sharedPreferences.getString("password", "");

		hashMap.put("username", username);
		hashMap.put("password", password);
		
		return hashMap;
	}

}
