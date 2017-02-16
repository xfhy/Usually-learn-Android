package com.itheima.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 2017年1月29日
 * 
 * XFHY
 * 
 * TODO
 */
public class FirstService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("xfhy", "onBind");
		return null;
	}

	//第一次创建时调用
	@Override
	public void onCreate() {
		Log.d("xfhy", "onCreate");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("xfhy", "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	//服务销毁时调用
	@Override
	public void onDestroy() {
		Log.d("xfhy", "onDestroy");
		super.onDestroy();
	}
	
}
