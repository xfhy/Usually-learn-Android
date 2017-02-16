package com.itheima.appstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 2017年1月25日
 * 
 * XFHY
 * 
 * TODO
 */
public class AppStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals("android.intent.action.PACKAGE_INSTALL")) {
			Log.d("xfhy", "有应用被安装啦");
		} else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
			Log.d("xfhy", "!!!!!!!有应用被安装啦");
		} else if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
			Log.d("xfhy", "有应用被卸载啦");
		}
	}

}
