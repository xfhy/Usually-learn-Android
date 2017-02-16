package com.itheima.receiverice.receiver;

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
public class FarmerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// [1]获取到发送广播携带的数据
		String content = getResultData();
		// [2]展示到Toast上
		Toast.makeText(context, "农民:" + content, Toast.LENGTH_SHORT).show();
	}

}
