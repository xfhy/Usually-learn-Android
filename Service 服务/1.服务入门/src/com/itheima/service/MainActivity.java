package com.itheima.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_start;
	private Button bt_stop;
	private Button bt_bind;
	private Button bt_unbind;
	Intent intent;
	private MyConn myConn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_start = (Button) findViewById(R.id.bt_start);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_bind = (Button) findViewById(R.id.bt_bind);
		bt_unbind = (Button) findViewById(R.id.bt_unbind);

		bt_start.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		bt_bind.setOnClickListener(this);
		bt_unbind.setOnClickListener(this);
		
		intent = new Intent(this,FirstService.class);
		
	}

	/**
	 * 开启服务
	 */
	private void mStartService(){
		startService(intent);
	}
	
	/**
	 * 关闭服务
	 */
	private void mStopService(){
		stopService(intent);
	}
	
	/**
	 * 绑定服务
	 */
	private void mBindService(){
		myConn = new MyConn();
		//绑定服务
		bindService(intent, myConn, BIND_AUTO_CREATE);
	}
	
	private class MyConn implements ServiceConnection{

		//当服务连接成功的时候调用
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("xfhy", "服务开启成功");
		}

		//当断开连接时调用
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("xfhy", "服务已销毁");
		}
		
	}
	
	/**
	 * 解绑服务
	 */
	private void mUnBindService(){
		//解绑服务
		unbindService(myConn);
	}
	
	@Override
	protected void onDestroy() {
		
		//解绑服务
		unbindService(myConn);
		
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_start:
			mStartService();
			break;
		case R.id.bt_stop:
			mStopService();
			break;
		case R.id.bt_bind:
			mBindService();
			break;
		case R.id.bt_unbind:
			mUnBindService();
			break;
		default:
			break;
		}
	}

}
