package com.xfhy.toggleview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.xfhy.toggleview.view.ToggleView;

public class MainActivity extends Activity {

	private ToggleView tv_switch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_switch = (ToggleView) findViewById(R.id.tv_switch);
		//tv_switch.setSwitchBackgroundResource(R.drawable.switch_background);  //设置背景图
		//tv_switch.setSlideButtonResource(R.drawable.slide_button);   //设置滑动按钮图片
		//tv_switch.setSwitchState(true);   //设置开关状态
		tv_switch.setOnSwitchStateUpdateListener(new ToggleView.OnSwitchStateUpdateListener() {
			
			@Override
			public void onStateUpdate(boolean state) {
				Toast.makeText(MainActivity.this, "onStateUpdate : "+state, Toast.LENGTH_SHORT).show();
			}
		});
	}

}
