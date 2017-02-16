package com.itheima.receivebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 2017年1月26日
 * 
 * XFHY
 * 
 * TODO
 */
public class ReceiveCustomReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//1.取出广播携带的数据
		String content = intent.getStringExtra("name");
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

}
