package com.itheima.login.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;

/**
 * 2017年1月14日16:59:52
 * 
 * @author XFHY 工具类
 */
public class UserInfoUtil {

	// 保存用户密码
	public static boolean saveUserInfo(Context context,String username, String userpassword) {
		
		//保存到SD中
		String path = Environment.getExternalStorageDirectory().getPath(); // 用户信息保存路径
		
		File file = new File(path, "userinfo.txt");
		FileOutputStream fileOutputStream = null;
		try {
			String userinfo = username + "##" + userpassword; // 用户信息 中间用 ## 分割
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(userinfo.getBytes());
			fileOutputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	// 读取用户名和密码
	public static Map<String, String> readUserInfo(Context context) {

		String path = Environment.getExternalStorageDirectory().getPath(); // 用户信息保存路径
		
		File file = new File(path, "userinfo.txt");
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;

		try {
			fileInputStream = new FileInputStream(file);
			bufferedReader = new BufferedReader(new InputStreamReader(
					fileInputStream));

			String userinfo = bufferedReader.readLine();
			String[] split = userinfo.split("##");
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("username", split[0]);
			hashMap.put("password", split[1]);
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

}
