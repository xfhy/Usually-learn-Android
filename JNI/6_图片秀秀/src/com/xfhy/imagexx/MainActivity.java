package com.xfhy.imagexx;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.mt.mtxx.image.JNI;

/**
 * 通过.so文件美化一张图片
 * @author xfhy
 *
 */
public class MainActivity extends Activity implements OnClickListener{

	private ImageView image;
	private Button bt_beautify;
	private Bitmap bm;
	private int width;
	private int height;
	private JNI jni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		jni = new JNI();   //实例化JNI类
		
		bt_beautify = (Button)findViewById(R.id.bt_beautify);
		image = (ImageView)findViewById(R.id.image);
		
		bt_beautify.setOnClickListener(this);
		
		bm = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
		width = bm.getWidth();   //获取图片的宽度
		height = bm.getHeight(); //获取图片的高度
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_beautify:
			int pixels[] = new int[width*height];
			//这个函数运行之后pixels数组就被修改了   bm的颜色信息就保存到了pixels数组中
			bm.getPixels(pixels, 0, width, 0, 0, width, height);  
			
			//argb 8888 alpha red green blue 8 8 8 8 32位2进制  int刚好可以存
			//直接调用C语言的函数进行处理    其实是直接处理的图片的颜色信息数组
			jni.StyleBaoColor(pixels, width, height);
			
			//生成一个新的Bitmap对象,   信息是上面C语言处理过的
			Bitmap bm2 = Bitmap.createBitmap(pixels, width, height, bm.getConfig());   
			image.setImageBitmap(bm2);
			
			break;
		default:
			break;
		}
	}

}
