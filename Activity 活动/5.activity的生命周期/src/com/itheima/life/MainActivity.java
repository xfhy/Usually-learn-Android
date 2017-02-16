package com.itheima.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	// 当Activity第一次创建的时候调用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("xfhy", "MainActivity onCreate--->当Activity第一次创建的时候调用");
		
		findViewById(R.id.bt1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
				startActivity(intent);
			}
		});
		
	}

	//当Activity被stopped,再次调用的时候调用
	@Override
	protected void onRestart() {
		Log.d("xfhy", "MainActivity onRestart--->当Activity被stopped,再次调用的时候调用");
		super.onRestart();
	}
	
	// 当Activity可见的时候被调用
	@Override
	protected void onStart() {
		Log.d("xfhy", "MainActivity onStart--->当Activity可见的时候被调用");
		super.onStart();
	}

	//当Activity可以获取焦点的时候
	@Override
	protected void onResume() {
		Log.d("xfhy", "MainActivity onResume--->当Activity可以获取焦点的时候");
		super.onResume();
	}
	
	//当Activity失去焦点的时候被调用
	@Override
	protected void onPause() {
		Log.d("xfhy", "MainActivity onPause--->当Activity失去焦点的时候被调用");
		super.onPause();
	}
	
	//当Activity不可见的时候被调用
	@Override
	protected void onStop() {
		Log.d("xfhy", "MainActivity onStop--->当Activity不可见的时候被调用");
		super.onStop();
	}
	
	// 当Activity被销毁的时候调用
	@Override
	protected void onDestroy() {
		Log.d("xfhy", "MainActivity onDestroy--->当Activity被销毁的时候调用");
		super.onDestroy();
	}

}
