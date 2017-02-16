package com.itheima.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 2017年1月25日
 * 
 * XFHY
 * 
 * TODO
 */
public class SmsListenerReceiver extends BroadcastReceiver {

	//当短信到来时  触发此方法
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("xfhy", "短信来啦");
		
		//1.获取发送短信的号码  和  内容
		Object[] objects = (Object[]) intent.getExtras().get("pdus");
		for (Object pdu : objects) {
			//2.获取SmsMessage实例
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
			
			//3.获取发送短信的内容
			String body = smsMessage.getMessageBody();
			//4.获取发送者
			String address = smsMessage.getOriginatingAddress();
			Log.d("xfhy", "body:"+body+"-----"+address);
		}
	}

}
