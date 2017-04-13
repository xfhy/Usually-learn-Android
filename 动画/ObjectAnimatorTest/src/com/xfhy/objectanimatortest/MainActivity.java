package com.xfhy.objectanimatortest;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	/**
	 * 多个效果放在一起    然后用ObjectAnimator执行动画
	 * @param view
	 */
	public void propertyValuesHolder(View view)  
    {  
		//该类保存有关属性的信息以及该动画中该属性应该占用的值。 
		//PropertyValuesHolder对象可以用于使用ValueAnimator或ObjectAnimator来创建动画，并在多个不同的属性上进行操作。
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,  
                0f, 1f);    //透明度      从1->0->1
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,     //缩放
                0, 1f);     //X轴缩放比例   
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,  
                0, 1f);  
        //实现依次执行动画,顺序是pvhX,pvhY,pvhZ
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();  
    }  

	/**
	 * 多个效果放在一起    然后用ObjectAnimator执行动画
	 * @param v
	 */
	public void rotateyAnimRun(final View v){
		//ofFloat设置动画作用的元素,作用的属性,动画开始,结束,以及中间的任意值 
		//rotation:旋转      X轴旋转360°      时间是1000ms
		//ObjectAnimator.ofFloat(v, "rotationX", 0.0F,360F).setDuration(1000).start();
		
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
