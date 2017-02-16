package com.itheima.loadbigimg;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	private Button bt_loadimg;
	private ImageView iv_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_loadimg = (Button) findViewById(R.id.bt_loadimg);
		iv_img = (ImageView) findViewById(R.id.iv_img);
		
		bt_loadimg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		//1.获取屏幕的分辨率  宽和高
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Point point = new Point();
		wm.getDefaultDisplay().getSize(point);   //获取屏幕的大小 并将信息放到point里面
		int screenWidth = point.x;
		int screenHeight = point.y;
		
		Log.d("xfhy", "屏幕的宽和高:"+screenWidth+"   "+screenHeight);
		
		//2.获取图片的分辨率  宽和高
		//创建bitmap工厂的配置参数
		Options option = new Options();
		   //这个设置为true 则不返回bitmap直接返回null  然后decodeFile()方法将图片的信息封装到Options里面
		option.inJustDecodeBounds = true;
		//根据路径加载图片  将图片转换成bitmap
		BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/dog.jpg",option);
		int imgWidth = option.outWidth;
		int imgHeight = option.outHeight;
		Log.d("xfhy", "图片的宽和高:"+imgWidth+"   "+imgHeight);
		
		//3.计算缩放比   到底缩放多少合适
		int scale = 1;
		int scalex = imgWidth/screenWidth;
		int scaley = imgHeight/screenHeight;
		//挑选其中缩放比较大的来
		if(scalex>scale && scalex>scaley){
			scale = scalex;
		}
		if(scaley>scale && scaley>scalex){
			scale = scaley;
		}
		Log.d("xfhy", "缩放比"+scale);
		
		//4.设置图片的缩放比 ,用来节约内存
		option.inSampleSize = scale;
		option.inJustDecodeBounds = false;   //设置这个为false   不然会返回null
		Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/dog.jpg", option);
		
		//5.设置图片
		iv_img.setImageBitmap(bitmap);    
	}


}
