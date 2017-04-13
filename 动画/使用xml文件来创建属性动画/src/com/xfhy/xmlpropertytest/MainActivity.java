package com.xfhy.xmlpropertytest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView mBall;
	private Button mBtStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBall = (ImageView) findViewById(R.id.ball);
		mBtStart = (Button) findViewById(R.id.bt_start);
		
		
	}

	/**
	 * 单独执行一个X的动画
	 * @param view
	 */
	public void scaleX(View view)  
    {  
        // 加载动画  
		//使用AnimatorInflater加载动画的资源文件，然后设置目标，就ok~~是不是很简单，这只是单纯横向的放大一倍~
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalex);  
        anim.setTarget(mBall);  //目标
        anim.start();     //开始执行动画
    }  
	
	/**
	 * 一起执行的动画
	 * @param view
	 */
	public void together(View view) {
		//加载动画
		Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scalex_and_y);
		
		//缩放、反转等都有中心点或者轴，默认中心缩放，和中间对称线为反转线，所以我决定这个横向，纵向缩小以左上角为中心点：
        //设置旋转和缩放视图的点的x位置。 默认情况下，枢轴点以对象为中心。 设置此属性将禁用此行为，并导致视图仅使用显式设置的pivotX和pivotY值。
		//很简单，直接给View设置pivotX和pivotY，然后调用一下invalidate，就ok了。
        mBall.setPivotX(0);
        mBall.setPivotY(0);
        mBall.invalidate();   //显示地调用invalidate
		
		animator.setTarget(mBall);   //设置目标
		animator.start();   //设置动画开始执行
	}
	
}
