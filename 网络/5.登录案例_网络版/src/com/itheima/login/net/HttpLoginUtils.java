package com.itheima.login.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.itheima.login.util.StreamUtils;

/**
 * 2017年1月20日
 * 
 * XFHY
 * 
 * 网络访问工具类
 */
public class HttpLoginUtils {
	/**
	 * 访问服务器,查看是否登录成功
	 * 
	 * @param userpassword
	 * @param username
	 * @return
	 */
	public static void requestNetForGetLogin(final Handler handler,
			final String username, final String userpassword) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 1.创建一个Url对象
					URL url = new URL(
							"http://192.168.191.1:8080/itheima74/servlet/LoginServlet?username="
									+ URLEncoder.encode(username, "utf-8")
									+ "&pwd="
									+ URLEncoder.encode(userpassword, "utf-8"));

					// 2.获取一个UrlConnection对象
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					// 3.为UrlConnection对象设置一些请求的参数,请求方式，连接的超时时间
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);

					// 4.在获取url请求的数据前需要判断响应码，200 ：成功,206:访问部分数据成功 300：跳转或重定向
					// 400：错误 500：服务器异常
					int code = connection.getResponseCode();
					if (code == 200) {
						// 5.获取有效数据，并将获取的流数据解析成String
						InputStream inputStream = connection.getInputStream();
						String result = StreamUtils.streamToString(inputStream);

						boolean issuccess = result.contains("success");
						Log.i("xfhy", result);
						if (issuccess) {

							// 创建一个Message对象,将数据封装进去
							Message msg = Message.obtain();
							msg.what = 1; // 知道的msg的code,在接收的时候可以根据what判断是哪个msg对象
							msg.obj = issuccess;

							// 发送数据到主线程
							handler.sendMessage(msg);
						}
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 访问服务器,查看是否登录成功 通过POST方式
	 * 
	 * @param handler
	 * @param username
	 * @param userpassword
	 * @return
	 */
	public static void requestNetForPostLogin(final Handler handler,
			final String username, final String userpassword) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 1.创建一个Url对象
					URL url = new URL(
							"http://192.168.191.1:8080/itheima74/servlet/LoginServlet");

					// 2.获取一个UrlConnection对象
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					// 3.为UrlConnection对象设置一些请求的参数,请求方式，连接的超时时间
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(5000);

					// 设置一些请求头的信息 field:http请求的请求头 newValue:请求头的值
					String body = "username="
							+ URLEncoder.encode(username, "utf-8") + "&pwd="
							+ URLEncoder.encode(userpassword, "utf-8");
					connection.setRequestProperty("Content-Length",
							body.length() + "");
					connection.setRequestProperty("Catch-Control", "max-age=0");
					connection.setRequestProperty("Origin",
							"http://192.168.191.1:8080");

					// 设置HttpUrlConnection可以写的内容
					connection.setDoOutput(true);
					// 获取一个OutputStream,并将内容写入该流
					connection.getOutputStream().write(body.getBytes());

					// 4.在获取url请求的数据前需要判断响应码，200 ：成功,206:访问部分数据成功 300：跳转或重定向
					// 400：错误 500：服务器异常
					int code = connection.getResponseCode();
					if (code == 200) {
						// 5.获取有效数据，并将获取的流数据解析成String
						InputStream inputStream = connection.getInputStream();
						String result = StreamUtils.streamToString(inputStream);

						Log.i("xfhy", "result:" + result);
						boolean issuccess = result.contains("success");
						if (issuccess) {

							// 创建一个Message对象,将数据封装进去
							Message msg = Message.obtain();
							msg.what = 2; // 知道的msg的code,在接收的时候可以根据what判断是哪个msg对象
							msg.obj = issuccess;

							// 发送数据到主线程
							handler.sendMessage(msg);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
