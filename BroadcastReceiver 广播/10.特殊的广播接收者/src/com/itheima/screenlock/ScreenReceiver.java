package com.itheima.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 2017年1月28日
 * 
 * XFHY
 * 
 * TODO
 */
public class ScreenReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 1.获取广播传过来的意图 action
		String action = intent.getAction();
		// 2.判断该action是哪种
		if ("android.intent.action.SCREEN_OFF".equals(action)) {
			System.out.println("屏幕锁屏了");
		} else if ("android.intent.action.SCREEN_ON".equals(action)) {
			System.out.println("屏幕打开了~~~");
		}
	}

}
