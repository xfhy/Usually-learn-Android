package com.itheima.playmusic;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_play = (Button) findViewById(R.id.bt_play);
		
		bt_play.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) {
		//1.初始化MediaPlayer
				final MediaPlayer mediaPlayer = new MediaPlayer();
				
				try {
					//2.设置资源
					//播放本地音频或者网络资源都是可以的
//					mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath()+"/ido.mp3");
					mediaPlayer.setDataSource("http://192.168.1.3:80/TownofWindmill.mp3");
					//3. 准备播放 
//					mediaPlayer.prepare();
					
					mediaPlayer.prepareAsync();
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
						
						@Override
						public void onPrepared(MediaPlayer mp) {
							//4. 开始播放
							mediaPlayer.start();
						}
					});
					
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}


}
