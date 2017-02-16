package com.itheima.localservice;

import com.itheima.remoteservice.Iservice;
import com.itheima.remoteservice.Iservice.Stub;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	private Button bt_remove;
	private MyConn conn;
	private Iservice iservice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_remove = (Button) findViewById(R.id.bt_remove);
		bt_remove.setOnClickListener(this);
		
		//1.创建隐式意图
		Intent intent = new Intent();
		intent.setAction("com.itheima.remote");
		
		//2.绑定服务
		conn = new MyConn();
		bindService(intent, conn, BIND_AUTO_CREATE);
		
	}

	//监听服务的状态
	private class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//3.获取中间人对象
			iservice = Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		//4.解除绑定
		unbindService(conn);
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		try {
			//5.调用远程服务里面的方法
			iservice.callTestMethod();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
