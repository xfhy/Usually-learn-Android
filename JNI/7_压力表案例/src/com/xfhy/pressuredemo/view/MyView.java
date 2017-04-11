package com.xfhy.pressuredemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 2017年4月10日
 * 
 * xfhy
 * 
 * 自定义的View
 * 用于动态显示压力
 */
public class MyView extends View {

	private Paint paint;
	private int mPressure = 0;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * @param context
	 */
	public MyView(Context context) {
		super(context);
		init();
	}

	private void init(){
		paint = new Paint();
		paint.setTextSize(25);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		if(mPressure < 40){
			paint.setColor(Color.GREEN);
		} else if(mPressure > 40 && mPressure < 70){
			paint.setColor(Color.YELLOW);
		}else {
			paint.setColor(Color.RED);
		}
		
		canvas.drawRect(50, 200-mPressure, 100, 200, paint);     //画矩形   竖着的
		canvas.drawText("当前压力值:"+mPressure, 50, 50, paint);   //画文字
	}
	
	public void setPressure(int pressure){
		this.mPressure = pressure;
		postInvalidate();    //当需要从一个不是UI线程里重绘   就需要调用此方法
	}
	
}
