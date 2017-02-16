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
public class ProvinceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// [1]获取到发送广播携带的数据
		String content = getResultData();
		// [2]展示到Toast上
		Toast.makeText(context, "省:" + content, Toast.LENGTH_SHORT).show();
		
		//2.1终止广播
		abortBroadcast();
		
		// [3]修改数据 (扣留大米)
		setResultData("习大大给每个村民发了500斤大米");
	}

}
