package com.itheima.tweenanim;

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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Build;

import com.itheima.tweenanim2.R;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView iv_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_trans = (Button) findViewById(R.id.bt_trans); // 透明
		Button bt_revolve = (Button) findViewById(R.id.bt_revolve); // 旋转
		Button bt_zoom = (Button) findViewById(R.id.bt_zoom); // 缩放
		Button bt_disp = (Button) findViewById(R.id.bt_disp); // 位移
		Button bt_go = (Button) findViewById(R.id.bt_go); // 一起执行

		iv_img = (ImageView) findViewById(R.id.iv_img);

		bt_trans.setOnClickListener(this);
		bt_revolve.setOnClickListener(this);
		bt_zoom.setOnClickListener(this);
		bt_disp.setOnClickListener(this);
		bt_go.setOnClickListener(this);

	}

	// 透明
	private void trans() {

		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
		
		iv_img.startAnimation(animation);
	}

	// 旋转
	private void revolve() {
		/**
		 * float fromDegrees  开始角度
		 *  float toDegrees,  结束角度
		 *   int pivotXType,  X相对于哪里旋转    自己  或者父亲
		 *    float pivotXValue,  X轴上的一个点,根据这个点进行旋转      自身的
		 *    int pivotYType,   Y相对于哪里旋转    自己  或者父亲
		 *    float pivotYValue  Y轴上的一个点,根据这个点进行旋转
		 * 参数:开始
		 */
		/*RotateAnimation raAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		raAnimation.setDuration(2000);   //设置动画执行时间
		raAnimation.setRepeatCount(1);  //重复次数
		raAnimation.setRepeatMode(Animation.REVERSE);   //动画执行模式
*/		
		Animation raAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		iv_img.startAnimation(raAnimation);  //开始执行动画
	}

	// 缩放
	private void zoom() {

		/**
		 * float fromX,    从多少倍开始变大
		 *  float toX,     变大到多少倍
		 *   float fromY, 
		 *   float toY,
		 *    int pivotXType,
		 *     float pivotXValue,   从哪里开始缩放 0.0f - 1.0f  左上角到右下角    0.5f就是从中间位置开始变大
		 *      int pivotYType,
		 *       float pivotYValue
		 */
		/*ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f,
				1.0f, 2.0f, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setRepeatCount(1);
		scaleAnimation.setRepeatMode(Animation.REVERSE);*/
		
		Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
		
		iv_img.startAnimation(scaleAnimation);
		
	}

	// 位移
	private void disp() {
		/**
		 * int fromXType,
		 *  float fromXValue,
		 *   int toXType, 
		 *   float toXValue,
		 *  int fromYType,
		 *   float fromYValue,
		 *    int toYType,
		 *     float toYValue
		 */     //x轴不变,y轴向下移动
		/*TranslateAnimation tAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 
				0, Animation.RELATIVE_TO_PARENT, 0.2f);
		tAnimation.setDuration(2000);
		tAnimation.setFillAfter(true);    //设置位移后是否回到原点
*///		tAnimation.setRepeatMode(Animation.REVERSE);
		
		Animation tAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
		
		iv_img.startAnimation(tAnimation);
	}

	//一起执行
	private void letUsGo(){
		Animation animationSet = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set);
		
		iv_img.startAnimation(animationSet);   //开始动画
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_trans:
			trans();
			break;
		case R.id.bt_revolve:
			revolve();
			break;
		case R.id.bt_zoom:
			zoom();
			break;
		case R.id.bt_disp:
			disp();
			break;
		case R.id.bt_go:
			letUsGo();
			break;
		default:
			break;
		}
	}

}
