package com.xfhy.viewanimate;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";

	private ImageView mBall;
	private float mScreenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;
		mBall = (ImageView) findViewById(R.id.id_ball);

	}

	/**
	 * 用View的animate()方法实现图片的简单下落
	 */
	public void viewAnim(View view) {

		// 简单的使用mBlueBall.animate().alpha(0).y(mScreenHeight /
		// 2).setDuration(1000).start()就能实现动画~~不过需要SDK11，
		// 此后在SDK12，SDK16又分别添加了withStartAction和withEndAction用于在动画前
		// ，和动画后执行一些操作。当然也可以.setListener(listener)等操作。
		mBall.animate().alpha(0) // 设置透明的逐渐减为0
				.y(mScreenHeight / 2).setDuration(1000) // 设置它的y坐标一直从0增大刀屏幕高度的一半
														// 时间是1秒
				.withStartAction(new Runnable() {

					@Override
					public void run() {
						Log.d("xfhy", "START");
					}
				}).withEndAction(new Runnable() { // 结束时

							@Override
							public void run() {
								Log.d("xfhy", "END");
								runOnUiThread(new Runnable() {

									@Override
									public void run() { // 结束时将Y的坐标设置为0,并且将透明的设置为1
										mBall.setY(0);
										mBall.setAlpha(1.0f);
									}
								});
							}
						}).start(); // 最后记得开始执行动画
	}

	/**
	 * 使用ObjectAnimator实现上面的变化，我们可以使用：PropertyValueHolder
	 */
	public void propertyValuesHolder(View view) {
		//搞一个PropertyValuesHolder实例    
		PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("alpha", 1.0f,0f,1.0f); //透明度:1->0->1
		PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("y", 0,mScreenHeight/2,0); //y值:0->下降->0
		//设置动画
		ObjectAnimator.ofPropertyValuesHolder(mBall,pvh1,pvh2).setDuration(2000).start();
		
	}

}
