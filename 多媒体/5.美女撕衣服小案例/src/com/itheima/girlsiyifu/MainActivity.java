package com.itheima.girlsiyifu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.itheima.girlsiyifu.R;

/**
 * 2017年2月9日11:40:48
 * 
 * @author xfhy
 *
 * 撕衣服  
 * 
 * 原理:放2张图片,上面张图片滑过的路线设置透明
 *
 */
public class MainActivity extends Activity implements OnTouchListener{

	private ImageView iv_img;
	private Bitmap copyBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv_img = (ImageView) findViewById(R.id.iv_img);
		
		iv_img.setOnTouchListener(this);
		
		//1.获取原始图片
		Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre19);
		
		//2.创建模板
		copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		//2.1 创建画布  以copyBitmap为模板
		Canvas canvas = new Canvas(copyBitmap);
		//2.2 创建画笔
		Paint paint = new Paint();
		//2.3 开始作画
		canvas.drawBitmap(srcBitmap, new Matrix(), paint);
		
		//3.设置图片
		iv_img.setImageBitmap(copyBitmap);
		
	}

	//触摸事件
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//4. 获取触摸事件的类型
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_MOVE:   //移动
			
			// 每一次 设置透明的区域稍微大一些 将滑过区域的-7<x<7,-7<y<7都设置成透明
			for (int i = -14; i < 14; i++) {
				for (int j = -14; j < 14; j++) {
					
					// 为了增强用户体验效果 将滑过的区域设置成圆形 的透明区域
					if (Math.sqrt(i * i + j * j) < 7) {
						try {
							// 5. 设置像素点的颜色
							// 参数 x,y,颜色 Color.TRANSPARENT是透明
							copyBitmap.setPixel((int) event.getX() + i, (int) event.getY() + j, Color.TRANSPARENT);
						} catch (Exception e) {
						}
					}

				}
			}
			
			//6. 最后一定要记得更新UI
			iv_img.setImageBitmap(copyBitmap);
			
			break;
		default:
			break;
		}
		
		//这里如果需要监听移动的那些事件则需要返回true
		return true;
	}


}
