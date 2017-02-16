package com.itheima.whybindservice;

import com.itheima.callmethodbyin.R;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	private MyConn myConn;

	private IService binder;

	private Button bt_test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_test = (Button) findViewById(R.id.bt_test);
		
		bt_test.setOnClickListener(this);
		
		//开启服务 
		Intent intent = new Intent(this,TestService.class);
		//连接服务 TestService
		myConn = new MyConn();
		//绑定服务 
		bindService(intent, myConn, BIND_AUTO_CREATE);
		
	}

	//监视服务的状态
	class MyConn implements ServiceConnection{

		//当连接服务成功后
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			////[4]获取我们定义的中间人对象
			binder = (IService) service;
		}

		//失去连接
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		unbindService(myConn);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		//通过我们定义的中间人对象 间接调用服务里面的方法
		binder.callbanzheng(1002);
		binder.callDaMaJiang();
	}
	
}
