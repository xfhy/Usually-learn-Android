package com.itheima.otherdo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * 2017年1月18日21:58:00
 * 
 * @author XFHY
 * 
 *         面试：子线程一定不能更新UI？ SurfaceView ：多媒体视频播放 ,可以在子线程中更新UI；
 *         Progress（进度）相关的控件：也是可以在子线程中更新Ui
 *         ;审计机制：activity完全显示的时候审计机制才会去检测子线程有没有更新Ui.
 * 
 */
public class MainActivity extends Activity {

	private Handler handler = new Handler(){
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView tv_text = (TextView)findViewById(R.id.tv_text);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//使用activity的runOnUiThread方法更新ui,无论当前线程是否是主线程，都将在主线程执行
//				runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						tv_text.setText("我被更新了");
//					}
//				});
				
				//使用handler直接post到主线程，handler需要在主线程创建
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						tv_text.setText("我被更新了");
					}
				}, 5000);  //延迟多少毫秒执行runnable。
				
//				tv_text.setText("我被更新了");
				
			}
		}).start();
	}

}
