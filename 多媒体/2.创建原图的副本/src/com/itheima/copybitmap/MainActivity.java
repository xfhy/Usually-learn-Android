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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
		ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
		
		//1.显示原图
		//getResources()获取所有资源
		Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
		iv_src.setImageBitmap(srcBitmap);
		
		//2.创建副本   copy原图
		Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		//2.1 创建画布   以copyBitmap为模型   
		Canvas canvas = new Canvas(copyBitmap);
		//2.2 创建画笔
		Paint paint = new Paint();
		//2.3 开始作画    srcBitmap参考原图作画
		canvas.drawBitmap(srcBitmap, new Matrix(), paint);
		
		iv_copy.setImageBitmap(copyBitmap);
	}

}
