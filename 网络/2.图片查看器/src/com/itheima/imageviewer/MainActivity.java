package com.itheima.imageviewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Context mContext;
	private EditText et_url;
	private ImageView img_source;

	//★★★1.在主线程中创建一个Handler对象
	private Handler handler = new Handler(){
		//★★★2.重写handleMessage方法   子类必须实现这个方法,用来接收子线程传递过来的数据    msg就是子线程传过来的数据
		public void handleMessage(Message msg) {
			//★★★5.接收子线程发送的数据,处理数据
			Bitmap bitmap = (Bitmap)msg.obj;
			
			//★★★6.当前方法属于主线程可以做UI的更新
			// 五.获取服务器返回的内容,显示到ImageView上
			img_source.setImageBitmap(bitmap);   //设置ImageView的内容
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		et_url = (EditText) findViewById(R.id.et_url);
		Button btn_ok = (Button) findViewById(R.id.btn_ok);
		img_source = (ImageView) findViewById(R.id.img_source);

		btn_ok.setOnClickListener(this);
	}

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
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					// 3.为UrlConnection对象设置一些请求的参数,请求方式,连接的超时时间
					connection.setRequestMethod("GET");
					connection.setReadTimeout(6000);

					// 4.在获取url请求的数据前需要判断响应码,200:成功  206:访问部分数据成功;300:跳转或重定向,400:错误,500:服务器异常
					int code = connection.getResponseCode();
					if (code == 200) {
						// 5.获取有效数据,并将获取的流数据解析成Bitmap
						InputStream inputStream = connection.getInputStream();
						//将一个读取流转换成一张图片,Bitmap:位图
						Bitmap responseImg = BitmapFactory.decodeStream(inputStream);
						
						//★★★3.子线程创建一个Message对象,为了携带子线程中获取的数据
						//这个的内部实现是:如果之前的Message存在则直接返回.不存在则创建一个新的Message进行返回
						Message msg = Message.obtain();   //这也是创建Message,这个比new Message更好
						msg.obj = responseImg;
						
						//★★★4.使用handler对象将message发送到主线程    sendMessage
						handler.sendMessage(msg);
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (ProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

}
