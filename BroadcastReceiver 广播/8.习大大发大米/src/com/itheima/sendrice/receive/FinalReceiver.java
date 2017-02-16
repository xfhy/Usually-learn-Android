package com.itheima.sendrice.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 2017年1月27日
 * 
 * XFHY
 * 
 *  作为有序广播的最后一个接收者resultReceiver   不需要在清单文件中配置
 */
public class FinalReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//1.获取广播发来的数据
		String content = getResultData();
		Log.d("xfhy", "收到习大大"+content);
		
	}
}
