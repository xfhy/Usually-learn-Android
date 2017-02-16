package com.itheima.smslistener;

import android.app.Activity;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 1.注册内容观察者
		Uri uri = Uri.parse("content://sms/");
		getContentResolver().registerContentObserver(uri, true,
				new MyContentObserver(new Handler()));

	}

	// 2.定义一个内容观察者
	class MyContentObserver extends ContentObserver {

		public MyContentObserver(Handler handler) {
			super(handler);
		}

		// 被监听的数据库,当数据库发送变化的时候调用
		@Override
		public void onChange(boolean selfChange) {
			Log.d("xfhy", "短信数据库内容发送变化");
			super.onChange(selfChange);
		}

	}

}
