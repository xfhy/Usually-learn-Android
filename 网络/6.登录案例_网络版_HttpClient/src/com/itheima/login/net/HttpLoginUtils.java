package com.itheima.login.net;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Handler;
import android.os.Message;

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
	 * 
	 * 访问服务器,查看是否登录成功
	 * 
	 * @param userpassword
	 * @param username
	 * @return
	 */
	public static void requestNetForGetLogin(final Handler handler,
			final String username, final String userpassword) {

		new Thread(new Runnable() {

			// 使用HttpClient请求服务器将用户密码发送服务器验证
			@Override
			public void run() {
				try {
					String path = "http://192.168.191.1:8080/itheima74/servlet/LoginServlet?username="
							+ URLEncoder.encode(username, "utf-8")
							+ "&pwd="
							+ URLEncoder.encode(userpassword, "utf-8");

					// 1.创建一个HttpClient对象
					HttpClient defaultHttpClient = new DefaultHttpClient();
					// 2.设置请求的方式
					HttpGet httpGet = new HttpGet(path);
					// 3.执行一个http请求
					HttpResponse response = defaultHttpClient.execute(httpGet);
					// 4.获取请求的状态码
					StatusLine statusLine = response.getStatusLine();
					int code = statusLine.getStatusCode();

					if (code == 200) {
						// 5.判断状态码后获取内容
						HttpEntity entity = response.getEntity();
						InputStream inputStream = entity.getContent();
						String result = StreamUtils.streamToString(inputStream);
						boolean issuccess = result.contains("success");

						// 创建一个Message对象,将数据封装进去
						Message msg = Message.obtain();
						msg.what = 1; // 知道的msg的code,在接收的时候可以根据what判断是哪个msg对象
						msg.obj = issuccess;

						// 发送数据到主线程
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
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

			// 使用HttpClient请求服务器将用户密码发送服务器验证
			@Override
			public void run() {
				try {
					String path = "http://192.168.191.1:8080/itheima74/servlet/LoginServlet";

					// 1.创建一个httpClient对象
					HttpClient httpClient = new DefaultHttpClient();

					// 2.创建一个请求方式
					HttpPost httpPost = new HttpPost(path);
					// 创建集合 封装数据
					ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
					BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
							"username", username);
					BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(
							"pwd", userpassword);
					arrayList.add(basicNameValuePair);
					arrayList.add(basicNameValuePair2);

					// 创建一个Entity
					UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(
							arrayList,"utf-8");
					// 设置请求时的内容
					httpPost.setEntity(encodedFormEntity);

					// 3.执行一个请求,返回一个response对象
					HttpResponse response = httpClient.execute(httpPost);

					// 4.获取请求的状态码
					int code = response.getStatusLine().getStatusCode();

					// 5.判断状态码后获取内容
					if (code == 200) {
						// 获取实体内容,其中封装的有http请求返回的流信息
						HttpEntity httpEntity = response.getEntity();
						InputStream inputStream = httpEntity.getContent();

						// 将流转换成字符串
						String result = StreamUtils.streamToString(inputStream);

						boolean issuccess = result.contains("success");

						// 创建一个Message对象,将数据封装进去
						Message msg = Message.obtain();
						msg.what = 2; // 知道的msg的code,在接收的时候可以根据what判断是哪个msg对象
						msg.obj = issuccess;

						// 发送数据到主线程
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
