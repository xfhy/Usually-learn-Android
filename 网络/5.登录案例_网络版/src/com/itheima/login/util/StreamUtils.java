package com.itheima.login.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StreamUtils {

	

	// 将inputStream转换成String的工具方法
	public static String streamToString(InputStream inputStream) {
		StringBuffer result = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "utf-8")); 
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result.toString();
	}

}
