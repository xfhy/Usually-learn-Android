package com.itheima.frameanim;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// [1]找到ImageView控件 用来显示动画效果
		ImageView rocketImage  = (ImageView) findViewById(R.id.iv_img);
		// [2]设置背景资源
		rocketImage.setBackgroundResource(R.drawable.girl_anim);
		// [3]获取AnimationDrawable 类型
		animationDrawable = (AnimationDrawable) rocketImage.getBackground();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			// [4]开始执行动画
			animationDrawable.start();   //开始动画
			return true;
		}
		return super.onTouchEvent(event);
	}
	
}
