package com.itheima.sendbrocdcast;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	private Button bt_send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_send = (Button) findViewById(R.id.bt_send);
		bt_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//1.创建Intent对象
		Intent intent = new Intent();
		//2.设置action
		intent.setAction("com.itheima.news");
		//3.设置需要传送的数据
		intent.putExtra("name", "新闻联播来啦!!!");
		//4.发送无序广播
		//Broadcast the given intent to all interested BroadcastReceivers. 
		sendBroadcast(intent);
	}

}
