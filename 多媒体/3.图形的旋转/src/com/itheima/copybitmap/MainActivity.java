package com.itheima.copybitmap;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

import com.itheima.copybitmap2.R;

public class MainActivity extends Activity {

	private float degrees = 20; // 小猫旋转的角度

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
		final ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);

		// 1.显示原图
		// getResources()获取所有资源
		final Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.tomcat);
		iv_src.setImageBitmap(srcBitmap);

		new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 100; i++) {
					// 2.创建副本 copy原图
					final Bitmap copyBitmap = Bitmap.createBitmap(
							srcBitmap.getWidth()+30, srcBitmap.getHeight()+30,
							srcBitmap.getConfig());
					// 2.1 创建画布 以copyBitmap为模型
					Canvas canvas = new Canvas(copyBitmap);
					// 2.2 创建画笔
					Paint paint = new Paint();

					// 2.3 设置旋转
					Matrix matrix = new Matrix();

					degrees += 5;

					// 参数 旋转角度,旋转的中点x,y
//					matrix.setRotate(degrees, srcBitmap.getWidth() / 2,
//							srcBitmap.getHeight() / 2);
					
					//缩放
//					matrix.setScale(0.5f, 0.5f);
					
					//位移
//					matrix.setTranslate(80, 0);
					
					//倒影效果
//					matrix.setScale(1.0f, -1.0f);    //x轴不变      y轴反转
//					//post是在上一次修改的基础上进行修改,set则是每次都是新的变化   会覆盖上一次的修改
//					matrix.postTranslate(0, srcBitmap.getHeight());
					
					//镜面效果
					matrix.setScale(-1.0f, 1.0f);
					matrix.postTranslate(srcBitmap.getWidth(), 0);
					
					// 2.4 开始作画 srcBitmap参考原图作画
					canvas.drawBitmap(srcBitmap, matrix, paint);

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							iv_copy.setImageBitmap(copyBitmap);
						}
					});

					SystemClock.sleep(1000);
				}

			}
		}).start();

	}

}
