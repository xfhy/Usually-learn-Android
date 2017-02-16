package com.itheima.phoneservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author  XFHY
 * @date  2017年1月30日 上午9:39:22
 * @package com.itheima.phoneservice.receiver
 * @function 当手机重启完成时    接收手机重启的广播   开启服务
 */
public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		//开启服务
		Intent intent2 = new Intent(context,BootReceiver.class);
		context.startService(intent2);
		
	}

}
