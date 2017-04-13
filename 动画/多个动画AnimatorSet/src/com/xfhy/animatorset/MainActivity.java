package com.xfhy.animatorset;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends Activity {

	private ImageView mBall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBall = (ImageView) findViewById(R.id.id_ball);

	}

	/**
	 * 多个动画一起执行
	 * @param view
	 * 实现X轴和Y轴方向的同时放大到2倍
	 */
	public void togetherRun(View view) {
		                                         //设置该View的scaleX   X轴的比例从1.0->2.0
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBall,"scaleX", 1.0f,2.0f);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBall, "scaleY", 1.0f,2.0f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.setDuration(2000);
		animSet.setInterpolator(new LinearInterpolator());
		animSet.playTogether(anim1,anim2);   //多个动画同时执行
		animSet.start();  //最后记得开始执行动画
	}

	/**
	 * 多个动画一起执行    多个动画顺序执行
	 * @param view
	 */
	public void playWithAfter(View view)  {
		float cx = mBall.getX();   //获取该mBall的X坐标
		
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBall, "alpha", 1.0f,0.5f);  //从1.0不透明到0.5半透明
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBall, "scaleX", 1.0f,2.0f); //X比例 变到2倍
		ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBall, "scaleY", 1.0f,2.0f);
		ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBall, "x", cx,0f);  //x  从当前位置到0
		ObjectAnimator anim5 = ObjectAnimator.ofFloat(mBall, "x", cx);  //x  位置到cx    恢复初始位置
		
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(anim1).with(anim2);   //anim1-anim4  一起执行
		animatorSet.play(anim2).with(anim3);
		animatorSet.play(anim3).with(anim4);  
		animatorSet.play(anim5).after(anim4);  //anim5在anim4执行之后才执行
		
		animatorSet.setDuration(1000);
		animatorSet.start();  //开始执行
	}
	
}
