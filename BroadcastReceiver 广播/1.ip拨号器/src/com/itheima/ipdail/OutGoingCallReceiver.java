package com.itheima.ipdail;

import com.itheima.ipdail.util.SharedUtils;

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
public class OutGoingCallReceiver extends BroadcastReceiver {

	//当接收到外拨电话的事件的时候会执行这个方法
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//0.从SharedPreferences文件中获取ip号码
		String ipNumber = SharedUtils.getNumber(context);
		
		//1.获取拨打的电话号码    
		//取回当前的结果数据,数据来自于先前发生的receiver,常常这个数据是null
		//Retrieve the current result data, as set by the previous receiver. Often this is null.
		String currentNumber = getResultData();
		
		if(currentNumber == null){
			return ;
		}
		
		//2.加上17951再设置回去
		if(currentNumber.startsWith("0")){
			setResultData(ipNumber+currentNumber);
		}
		
		Log.d("xfhy", "onReceive");
		
	}

}
