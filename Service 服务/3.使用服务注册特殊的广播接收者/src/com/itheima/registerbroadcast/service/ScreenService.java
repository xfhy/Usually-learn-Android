package com.itheima.registerbroadcast.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import com.itheima.registerbroadcast.receiver.ScreenReceiver;

/**
 * @author  XFHY
 * @date  2017年1月30日 上午10:27:01
 * @package com.itheima.registerbroadcast.service
 * @function 这是服务   用于开启监听屏幕状态的广播   这种广播非常频繁,属于特殊的广播,所有在清单文件中注册了也没用
 * 需要在代码中动态的注册
 */
public class ScreenService extends Service {

	
	
	private ScreenReceiver screenReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		
		//1.实例化广播接收者
		screenReceiver = new ScreenReceiver();
		//2.实例化IntentFilter对象
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.SCREEN_OFF");
		intentFilter.addAction("android.intent.action.SCREEN_ON");
		
		//3.注册广播接收者
		registerReceiver(screenReceiver, intentFilter);
		
		Toast.makeText(this, "监听屏幕状态的广播已开启", Toast.LENGTH_SHORT).show();
		
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		//4.取消注册广播接收者
		unregisterReceiver(screenReceiver);
		
		super.onDestroy();
	}
	
}
