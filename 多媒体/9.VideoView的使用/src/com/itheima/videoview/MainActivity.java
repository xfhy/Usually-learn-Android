package com.itheima.videoview;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// [1]插件vitamio框架检查是否可用
		if (!LibsChecker.checkVitamioLibs(this)) {
			return;
		}

		final VideoView vv_video = (VideoView) findViewById(R.id.vv_video);

		// 2. 设置播放的路径
		vv_video.setVideoPath(Environment.getExternalStorageDirectory()
				.getPath() + "/cc.MP4");
		// 3. 设置准备的监听
		vv_video.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				// 4.开始播放
				vv_video.start();
			}
		});
		//4. 设置video的控制器 设置一个进度条
		vv_video.setMediaController(new MediaController(this));
	}

}
