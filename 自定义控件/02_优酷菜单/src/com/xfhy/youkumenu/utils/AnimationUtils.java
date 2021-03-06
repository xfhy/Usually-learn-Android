package com.xfhy.youkumenu.utils;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * @author xfhy
 * @date 2017年3月29日 上午11:07:49
 * @package com.xfhy.youkumenu.utils
 * @function 下面是用的补间动画 补间动画不会改变控件真实的坐标
 */
public class AnimationUtils {

	public static int runningAnimationCount = 0; // 记录当前有多少个正在执行的动画
													// 如果大于0则不需要执行动画

	/**
	 * 播放RelativeLayout的动画 转出
	 * 
	 * @param layout
	 */
	public static void rotateOutAnim(RelativeLayout layout, long delay) {

		// 当layout转出时,禁用这些组件
		int childCount = layout.getChildCount();
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(false);
		}

		RotateAnimation raAnimation = new RotateAnimation(0f, -180f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				1.0f);
		raAnimation.setFillAfter(true); // 动画播放完后,控件不回来(其实是在原地,只是不可见)
		raAnimation.setDuration(500); // 动画执行时间
		raAnimation.setStartOffset(delay); // 设置动画延迟

		raAnimation.setAnimationListener(new MyAnimationListener()); // 设置监听

		layout.startAnimation(raAnimation); // 开始动画
	}

	/**
	 * 播放RelativeLayout的动画 转入
	 * 
	 * @param layout
	 */
	public static void rotateInAnim(RelativeLayout layout, long delay) {

		// 当layout转入时,启用这些组件
		int childCount = layout.getChildCount();
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(true);
		}

		RotateAnimation raAnimation = new RotateAnimation(-180f, 0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				1.0f);
		raAnimation.setFillAfter(true); // 动画播放完后,控件不回来(其实是在原地,只是不可见)
		raAnimation.setDuration(500); // 动画执行时间
		raAnimation.setStartOffset(delay); // 设置开始执行的延迟

		raAnimation.setAnimationListener(new MyAnimationListener()); // 设置监听

		layout.startAnimation(raAnimation); // 开始动画
	}

	static class MyAnimationListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			runningAnimationCount++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			runningAnimationCount--;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

}
