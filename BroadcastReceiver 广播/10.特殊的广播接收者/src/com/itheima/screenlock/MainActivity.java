package com.itheima.screenlock;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	private ScreenReceiver screenReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* <receiver android:name="com.itheima.screen.ScreenReceiver">
        <intent-filter >
            <action android:name="android.intent.action.SCREEN_OFF"/>
            <action android:name="android.intent.action.SCREEN_ON"/>
        </intent-filter>
    </receiver>*/
		
		//1.动态的去注册屏幕解锁 和锁屏的广播
		screenReceiver = new ScreenReceiver();
		//2.创建IntentFilter对象
		IntentFilter intentFilter = new IntentFilter();
		//3.添加要注册的action
		intentFilter.addAction("android.intent.action.SCREEN_OFF");
		intentFilter.addAction("android.intent.action.SCREEN_ON");
		
		//注册广播接收者
		this.registerReceiver(screenReceiver, intentFilter);
		
	}


	@Override
	protected void onDestroy() {
		//当activity销毁的时候  取消注册广播接收者
		this.unregisterReceiver(screenReceiver);
		super.onDestroy();
	}
	
}
