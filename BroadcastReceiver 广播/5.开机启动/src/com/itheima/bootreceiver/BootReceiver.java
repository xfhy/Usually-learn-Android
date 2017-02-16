package com.itheima.bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 2017年1月25日
 * 
 * XFHY
 * 
 * TODO
 */
public class BootReceiver extends BroadcastReceiver {

	//当手机重启时   会  执行这个方法
	@Override
	public void onReceive(Context context, Intent intent) {
		//开启mainActivity
		Intent intent2 = new Intent(context,MainActivity.class);
		
		//☆ 如果在广播里面开启Activity 要设置一个任务栈环境
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		//在广播接收者里面开启activity
		context.startActivity(intent2);
	}

}
