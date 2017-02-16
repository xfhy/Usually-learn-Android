package com.itheima.news_listview.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 2017年1月19日
 * 
 * XFHY
 * 
 * 自定义控件    实现通过网络访问加载图片
 */
public class MyImageView extends ImageView {

	
	/**
	 * @param context
	 */
	public MyImageView(Context context) {
		super(context);
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			Bitmap bitmap = (Bitmap)msg.obj;
			
			if(bitmap != null){
				MyImageView.this.setImageBitmap(bitmap);
			}
			
		};
	};
	
	public void setImageUrl(final String url_str){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					URL url = new URL(url_str);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setConnectTimeout(5000);
					connection.setRequestMethod("GET");
					int code = connection.getResponseCode();
					if(code == 200){
						InputStream inputStream = connection.getInputStream();
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						
						//封装数据到Message对象中
						Message msg = Message.obtain();
						msg.obj = bitmap; 
						
						//将数据发往主线程
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
