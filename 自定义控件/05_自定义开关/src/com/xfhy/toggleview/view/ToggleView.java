package com.xfhy.toggleview.view;

import com.xfhy.toggleview.R;
import com.xfhy.toggleview.view.ToggleView.OnSwitchStateUpdateListener;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author xfhy
 * @date 2017年3月30日 下午6:17:49
 * @package com.xfhy.toggleview.view
 * @function 自定义开关
 * 
 *           Android的界面绘制流程 测量 摆放 绘制 measure -> layout -> draw | | | onMeasure
 *           -> onLayout -> onDraw重写这些方法,实现自定义控件
 * 
 *           onResume()之后才执行这些方法
 * 
 *           View onMeasure()(在这个方法里指定自己的宽高) -> onDraw(绘制自己的内容)
 * 
 *           ViewGroup onMeasure()(指定自己的宽高,所有子View的宽高) -> onLayout()(摆放所有子View)
 *           -> onDraw(绘制内容)
 * 
 */
public class ToggleView extends View {

	private boolean mSwitchState = false; // 开关状态
	private Bitmap mSwitchBackgroundBitmap;
	private Bitmap mSlideButtonBitmap;
	private Paint mPaint;
	private boolean isTouchState = false; // 是否处于点击状态
	private float currentX; // 当前点击的坐标
	private OnSwitchStateUpdateListener onSwitchStateUpdateListener;

	/**
	 * 用户代码创建控件
	 * 
	 * @param context
	 */
	public ToggleView(Context context) {
		super(context);
		init();
	}

	/**
	 * 用于在xml中使用,可指定自定义属性
	 * 
	 * @param context
	 * @param attrs
	 */
	public ToggleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// 获取配置的自定义属性   xmlns:attr="http://schemas.android.com/apk/res/com.xfhy.toggleview"
		String namespace = "http://schemas.android.com/apk/res/com.xfhy.toggleview";
		int switchBackgroundResource = attrs.getAttributeResourceValue(namespace , "switch_background", -1);
		int slideButtonResource = attrs.getAttributeResourceValue(namespace, "slide_button", -1);
		boolean switchState = attrs.getAttributeBooleanValue(namespace, "switch_state", false);
		
		setSwitchBackgroundResource(switchBackgroundResource);
		setSlideButtonResource(slideButtonResource);
		setSwitchState(switchState);
		
	}

	/**
	 * 用于在xml中使用,可指定自定义的属性,如果指定了样式,则走此构造函数
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/**
	 * 测量View
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 根据背景图的大小 既是 View的大小
		setMeasuredDimension(mSwitchBackgroundBitmap.getWidth(),
				mSwitchBackgroundBitmap.getHeight());
	}

	/**
	 * 画图
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 1.画背景图 bitmap ,起始x,y坐标 画笔
		canvas.drawBitmap(mSwitchBackgroundBitmap, 0, 0, mPaint);

		// 2.画前景图(滑动Button) 需要根据当前的开关状态来画图
		if (isTouchState) { // 当前处于点击状态
			// 需要从哪个X开始画 如果这里是0的话,鼠标滑动的位置不是在滑动按钮的中间,给人感觉不好
			float newLeft = currentX - mSlideButtonBitmap.getWidth() / 2.0f;
			float maxLeft = mSwitchBackgroundBitmap.getWidth()
					- mSlideButtonBitmap.getWidth(); // 右边最大能到达的范围
			if (newLeft < 0) {
				newLeft = 0; // 左边范围
			} else if (newLeft > maxLeft) {
				// 右边范围
				newLeft = maxLeft;
			}

			canvas.drawBitmap(mSlideButtonBitmap, newLeft, 0, mPaint);
		} else { // 未处于点击状态
			if (mSwitchState) { // 开
				// 背景宽度-滑动按钮宽度
				canvas.drawBitmap(
						mSlideButtonBitmap,
						mSwitchBackgroundBitmap.getWidth()
								- mSlideButtonBitmap.getWidth(), 0, mPaint);

			} else { // 关
				// 画bitmap bitmap对象,起始点的x,y 画笔
				canvas.drawBitmap(mSlideButtonBitmap, 0, 0, mPaint);
			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: // 按下
			isTouchState = true;
			currentX = event.getX(); // 获取当前点击的位置
			Log.d("xfhy", "currentX: " + currentX);
			break;
		case MotionEvent.ACTION_MOVE: // 移动
			currentX = event.getX(); // 获取当前点击的位置
			Log.d("xfhy", "currentX: " + currentX);
			break;
		case MotionEvent.ACTION_UP: // 抬起
			currentX = event.getX(); // 获取当前点击的位置
			isTouchState = false;
			Log.d("xfhy", "currentX: " + currentX);

			int centerX = mSwitchBackgroundBitmap.getWidth() / 2; // View的中点
			boolean state = centerX < currentX; // 判断最后应该在开 还是 关

			//当抬起时   检查当前开关状态是否与之前的状态相同
			if(state != mSwitchState && onSwitchStateUpdateListener != null) {
				onSwitchStateUpdateListener.onStateUpdate(state);   //接口回调
			} 
			
			mSwitchState = state; // 开关

			break;
		default:
			break;
		}

		invalidate(); // 重绘界面 会引发onDraw()方法被调用,里面的变量会重新生效,界面会更新

		return true; // 返回true 表示消费了该事件
	}

	/**
	 * 初始化
	 */
	private void init() {
		mPaint = new Paint();
	}

	/**
	 * 设置背景图
	 * 
	 * @param switchBackground
	 */
	public void setSwitchBackgroundResource(int switchBackground) {
		mSwitchBackgroundBitmap = BitmapFactory.decodeResource(getResources(),
				switchBackground);
	}

	/**
	 * 设置滑块图片资源
	 * 
	 * @param slideButton
	 */
	public void setSlideButtonResource(int slideButton) {
		mSlideButtonBitmap = BitmapFactory.decodeResource(getResources(),
				slideButton);
	}

	/**
	 * 控制开关状态
	 * 
	 * @param switchState
	 */
	public void setSwitchState(boolean switchState) {
		this.mSwitchState = switchState;
	}

	/**
	 * 开关状态监听器
	 * @author xfhy
	 *
	 */
	public interface OnSwitchStateUpdateListener {
		/**
		 * 状态回调 把当前状态传出去
		 * @param state 当前状态
		 */
		void onStateUpdate(boolean state);
	}

	/**
	 * 设置监听器
	 * @param onSwitchStateUpdateListener
	 */
	public void setOnSwitchStateUpdateListener(
			OnSwitchStateUpdateListener onSwitchStateUpdateListener) {
		this.onSwitchStateUpdateListener = onSwitchStateUpdateListener;
	}

}
