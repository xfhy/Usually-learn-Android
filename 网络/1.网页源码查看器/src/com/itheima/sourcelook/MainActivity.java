package com.itheima.sourcelook;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.itheima.sourcelook.util.StreamUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Context mContext;
	private EditText et_url;
	private TextView tv_source;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		et_url = (EditText) findViewById(R.id.et_url);
		Button btn_ok = (Button) findViewById(R.id.btn_ok);
		tv_source = (TextView) findViewById(R.id.tv_source);

		btn_ok.setOnClickListener(this);
		
		Log.i("xfhy", "onCreate线程:"+Thread.currentThread().getName());
	}

	//★★★1.在主线程中创建一个Handler对象
		private Handler handler = new Handler(){
			      //★★★2.重写handleMessage方法   子类必须实现这个方法,用来接收子线程传递过来的数据    msg就是子线程传过来的数据
			public void handleMessage(android.os.Message msg) {
				//★★★5.接收子线程发送的数据,处理数据
				String result= (String) msg.obj;
				Log.i("xfhy", "handler线程:"+Thread.currentThread().getName());
				
				//★★★6.当前方法属于主线程可以做UI的更新
				// 五.获取服务器返回的内容,显示到textView上
				tv_source.setText(result);
			};
		};
	
	@Override
	public void onClick(View v) {
		// 三.onclick方法中获取用户输入的url地址
		final String urlString = et_url.getText().toString().trim();

		if (TextUtils.isEmpty(urlString)) {
			Toast.makeText(mContext, "url不能为空", Toast.LENGTH_SHORT).show();
			return;
		}

		// 四.请求url地址

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 1.创建一个url对象
					URL url = new URL(urlString);

					// 2.获取一个UrlConnection对象
					HttpURLConnection openConnection = (HttpURLConnection) url
							.openConnection();

					// 3.为UrlConnection对象设置一些请求的参数,请求方式,连接的超时时间
					openConnection.setRequestMethod("GET"); // 请求方式
					openConnection.setReadTimeout(6000); // 超时时间

					// 4.在获取url请求的数据前需要判断响应码,200:成功
					// 206:访问部分数据成功;300:跳转或重定向,400:错误,500:服务器异常
					if (openConnection.getResponseCode() == 200) {
						// 5.获取有效数据,并将获取的流数据解析成String
						InputStream inputStream = openConnection
								.getInputStream();
						String result = StreamUtils.streamToString(inputStream);

						//★★★3.子线程创建一个Message对象,为了携带子线程中获取的数据
						Message msg = new Message();
						msg.obj = result;    //将获取的数据封装到msg中
						//★★★4.使用handler对象将message发送到主线程    sendMessage
						handler.sendMessage(msg);
						
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

}
