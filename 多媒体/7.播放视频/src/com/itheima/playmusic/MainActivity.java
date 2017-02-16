package com.itheima.playmusic;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.itheima.playvideo.R;

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer;
	private int currentPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//SurfaceView是重量级的控件,这里找到了之后不会立刻就初始化,需要一点儿时间才行
		SurfaceView sv_video = (SurfaceView) findViewById(R.id.sv_video);

		
		
		// 设置视频播放内容的SurfaceHolder 是用来维护视频播放的内容
		SurfaceHolder holder = sv_video.getHolder();
		// 为此持有者添加回调接口。 有多个回调接口与持有者相关联。
		holder.addCallback(new Callback() {

			//当SurfaceView被销毁  则立即调用此方法
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				//如果MediaPlayer不为0   并且正在播放
				if(mediaPlayer != null && mediaPlayer.isPlaying()){
					//获取当前播放位置    用于下次继续播放
					currentPosition = mediaPlayer.getCurrentPosition();
					//停止播放
					mediaPlayer.stop();
				}
			}
			
			//当SurfaceView初始化完成了
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//1. 初始化MediaPlayer
				mediaPlayer = new MediaPlayer();
				try {
					//2. 设置播放的资源
					mediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/cc.MP4");
					
					//3. 设置显示
					mediaPlayer.setDisplay(holder);
					
					//4.准备播放(异步)
					mediaPlayer.prepareAsync();
					
					//当准备好了就会调用
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
						
						@Override
						public void onPrepared(MediaPlayer mp) {
							//5. 开始播放
							mediaPlayer.start();
							
							//6. 继续上次播放的位置
							mediaPlayer.seekTo(currentPosition);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
			}
		});

	}

}
