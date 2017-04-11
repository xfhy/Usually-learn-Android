package com.xfhy.objectanimatortest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	public void rotateyAnimRun(final View v){
		//ofFloat设置动画作用的元素,作用的属性,动画开始,结束,以及中间的任意值 
		//rotation:旋转      X轴旋转360°      时间是1000ms
		//ObjectAnimator.ofFloat(v, "rotationX", 0.0F,360F).setDuration(1000).start();
		
		/*
		 *  ObjectAnimator anim = ObjectAnimator//  
            .ofFloat(view, "zhy", 1.0F,  0.0F)//  
            .setDuration(500);//  
    anim.start();  
    anim.addUpdateListener(new AnimatorUpdateListener()  
    {  
        @Override  
        public void onAnimationUpdate(ValueAnimator animation)  
        {  
            float cVal = (Float) animation.getAnimatedValue();  
            view.setAlpha(cVal);  
            view.setScaleX(cVal);  
            view.setScaleY(cVal);  
        }  
    });  
		 * */
		
		ObjectAnimator animator = ObjectAnimator
				.ofFloat(v, "xfhy", 1.0F,0.0F)
				.setDuration(1000);
		animator.start();
		
		/**
		 * 此接口的实现可以添加自己为更新听众的ValueAnimator实例能够接收每一个动画帧上的回调，
		 * 当前帧的值已经计算出该ValueAnimator后。
		 */
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			//通知动画的另一帧的出现。
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				//ValueAnimator    该类提供了一个简单的计时引擎，用于运行动画，计算动画值并将其设置在目标对象上。
				
				//当ValueAnimator只有一个属性是动画时计算的最新值。 这个值只有在动画运行时才有意义。 
				//此只读属性的主要目的是在调用onAnimationUpdate（ValueAnimator）时从ValueAnimator中检索值
				//，该值在每个动画帧之间调用，紧随该值计算。
				float cVal = (float) animation.getAnimatedValue();
				v.setAlpha(cVal);
				v.setScaleX(cVal);
				v.setScaleY(cVal);
			}
		});
		
	}
	
}
