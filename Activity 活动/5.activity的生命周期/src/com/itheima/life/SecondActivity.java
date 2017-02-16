package com.itheima.life;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 2017年1月24日
 * 
 * XFHY
 * 
 * TODO
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Log.d("xfhy", "SecondActivity onCreate--->当Activity第一次创建的时候调用");
	}

	// 当Activity被stopped,再次调用的时候调用
	@Override
	protected void onRestart() {
		Log.d("xfhy", "SecondActivity onRestart--->当Activity被stopped,再次调用的时候调用");
		super.onRestart();
	}

	// 当Activity可见的时候被调用
	@Override
	protected void onStart() {
		Log.d("xfhy", "SecondActivity onStart--->当Activity可见的时候被调用");
		super.onStart();
	}

	// 当Activity可以获取焦点的时候
	@Override
	protected void onResume() {
		Log.d("xfhy", "SecondActivity onResume--->当Activity可以获取焦点的时候");
		super.onResume();
	}

	// 当Activity失去焦点的时候被调用
	@Override
	protected void onPause() {
		Log.d("xfhy", "SecondActivity onPause--->当Activity失去焦点的时候被调用");
		super.onPause();
	}

	// 当Activity不可见的时候被调用
	@Override
	protected void onStop() {
		Log.d("xfhy", "SecondActivity onStop--->当Activity不可见的时候被调用");
		super.onStop();
	}

	// 当Activity被销毁的时候调用
	@Override
	protected void onDestroy() {
		Log.d("xfhy", "SecondActivity onDestroy--->当Activity被销毁的时候调用");
		super.onDestroy();
	}
}
