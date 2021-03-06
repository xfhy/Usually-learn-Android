package com.xfhy.pressuredemo;

import com.xfhy.pressuredemo.view.MyView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	static {
		System.loadLibrary("pressure");
	}
	
	private Button start;
	private Button stop;
	private MyView pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pb = (MyView) findViewById(R.id.pb);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					startPressure();
				}
			}).start();
			
			break;
		case R.id.stop:
			stopPressure();
			break;
		default:
			break;
		}
	}

	/**
	 * 设置进度
	 */
	public void setMyProgress(int progress){
		pb.setPressure(progress);
	}
	
	/**
	 * 开始检测
	 */
	public native void startPressure();
	
	/**
	 * 停止检测
	 */
	public native void stopPressure();
	
}
