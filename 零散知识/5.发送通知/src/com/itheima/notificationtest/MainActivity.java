package com.itheima.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_send;
	private Button bt_close;
	private NotificationManager notificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_send = (Button) findViewById(R.id.bt_send);
		bt_close = (Button) findViewById(R.id.bt_close);

		bt_send.setOnClickListener(this);
		bt_close.setOnClickListener(this);

		//1. 获取NotificationManager实例
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	private void sendNoti(){
		
		//创建打电话的Intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+112));
		//延时的Intent
		PendingIntent callPhone = PendingIntent.getActivity(getApplicationContext(), 1, intent,Intent.FLAG_ACTIVITY_NEW_TASK);
		
		//2. 创建Notification对象     这是高版本的写法    链式调用
		Notification notification = new Notification.Builder(this)
				.setContentTitle("我是大标题")
				.setContentText("我是内容")
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(callPhone)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
				.build();
		
		//实现呼吸灯闪烁   并且振动
		notification.defaults = Notification.DEFAULT_ALL;
		
		//设置为不能删除通知
		notification.flags = Notification.FLAG_NO_CLEAR;
		
				/*------------------------------兼容低版本的写法  用过时的方法---------------------------------*/
		//Notification noti = new Notification(R.drawable.ic_launcher, "接收到了一条通知", System.currentTimeMillis());
		//noti.setLatestEventInfo(this, "小芳", "今天晚上7天酒店....", callPhone);
		
		//3. 发送通知   id是通知的标识
		notificationManager.notify(10, notification);
	}
	
	private void closeNoti(){
		//取消通知
		notificationManager.cancel(10);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_send:
			sendNoti();
			break;
		case R.id.bt_close:
			closeNoti();
			break;
		default:
			break;
		}
	}

}
