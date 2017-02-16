package com.itheima.sdcardstate;

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
public class SdcardReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		if(action.equals("android.intent.action.MEDIA_MOUNTED")){
			Log.d("xfhy", "sd卡被挂载");
		} else if(action.equals("android.intent.action.MEDIA_UNMOUNTED")){
			Log.d("xfhy", "sd卡被卸载");
		} 
	}

}
