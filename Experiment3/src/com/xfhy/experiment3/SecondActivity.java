package com.xfhy.experiment3;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class SecondActivity extends Activity implements OnClickListener{

	public final static String NAME_KEY = "name";
	public final static String AGE_KEY = "age";
	private Button bt_ok;
	private TextView tv_first_info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		bt_ok = (Button) findViewById(R.id.bt_ok);
		tv_first_info = (TextView) findViewById(R.id.tv_first_info);
		
		bt_ok.setOnClickListener(this);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra(NAME_KEY);
		int age = intent.getIntExtra(AGE_KEY, 0);
		tv_first_info.setText("接收到MainActivity数据: "+name+"  "+age);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = getIntent();
		intent.putExtra("info", "你好啊,MainActivity");
		setResult(RESULT_OK,intent);
		finish();   //销毁当前Activity
	}

	
}
