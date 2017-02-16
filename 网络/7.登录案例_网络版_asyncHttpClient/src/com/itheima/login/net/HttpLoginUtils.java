package com.itheima.login.net;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.itheima.login.util.StreamUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
	public static void requestNetForGetLogin(final Context context,
			final String username, final String userpassword) {

		try {
			String path = "http://192.168.191.1:8080/itheima74/servlet/LoginServlet?username="
					+ URLEncoder.encode(username, "utf-8")
					+ "&pwd="
					+ URLEncoder.encode(userpassword, "utf-8");

			// 创建一个AsyncHttpClient对象 这个直接在主线程运行
			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
			asyncHttpClient.get(path, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					// statusCode:状态码 headers:头信息 responseBody;返回的内容,返回的实体
					// 判断状态码
					if (statusCode == 200) {
						// 获取结果
						try {
							String result = new String(responseBody, "utf-8");
							Toast.makeText(context, result, Toast.LENGTH_SHORT)
									.show();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {

				}
			});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 访问服务器,查看是否登录成功 通过POST方式
	 * 
	 * @param handler
	 * @param username
	 * @param userpassword
	 * @return
	 */
	public static void requestNetForPostLogin(final Context context,
			final String username, final String userpassword) {
		try {
			String path = "http://192.168.191.1:8080/itheima74/servlet/LoginServlet";

			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
			RequestParams requestParams = new RequestParams();
			requestParams.put("username", username);
			requestParams.put("pwd", userpassword);

			// url: parmas：请求时携带的参数信息 responseHandler：是一个匿名内部类接受成功或失败 回调机制
			asyncHttpClient.post(path, requestParams,
					new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode, Header[] headers,
								byte[] responseBody) {
							// statusCode:状态码 headers：头信息
							// responseBody：返回的内容，返回的实体

							// 判断状态码
							if (statusCode == 200) {
								try {
									String result = new String(responseBody,
											"utf-8");

									Toast.makeText(context, result,
											Toast.LENGTH_SHORT).show();
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] responseBody, Throwable error) {

						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
