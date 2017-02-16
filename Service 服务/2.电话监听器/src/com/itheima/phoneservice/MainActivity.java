package com.itheima.phoneservice;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_start;
	private Button bt_stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_start = (Button) findViewById(R.id.bt_start);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_start.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, PhoneService.class);

		switch (v.getId()) {
		case R.id.bt_start:
			// 开启服务
			startService(intent);
			break;
		case R.id.bt_stop:
			//关闭服务
			stopService(intent);
			break;
		default:
			break;
		}

		
	}

}
