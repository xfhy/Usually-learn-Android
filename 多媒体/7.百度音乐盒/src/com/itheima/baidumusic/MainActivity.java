package com.itheima.baidumusic;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private Button bt_play;
	private Button bt_pause;
	private Button bt_re;
	private MyConn myConn;

	private Iservice iservice;
	private static SeekBar sb_seekm;
	private static TextView tv_current_time;
	private static TextView tv_duration_time;
	
	//接收服务中传递过来的歌曲信息(歌曲总长,歌曲当前进度)
	public static Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//获取服务中发送数据中携带的Bundle对象里面的数据
			Bundle data = msg.getData();
			int duration = data.getInt("duration");
			int currentPosition = data.getInt("currentPosition");
			
			Log.d("xfhy", "duration:"+duration+"    currentPosition:"+currentPosition);
			
			
			//设置当前时间
			manageTime(duration,currentPosition);
			
			sb_seekm.setMax(duration);   //设置进度条最大值
			sb_seekm.setProgress(currentPosition);  //设置进度条当前进度
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_play = (Button) findViewById(R.id.bt_play);
		bt_pause = (Button) findViewById(R.id.bt_pause);
		bt_re = (Button) findViewById(R.id.bt_re);
		sb_seekm = (SeekBar) findViewById(R.id.sb_seekm);
		tv_current_time = (TextView) findViewById(R.id.tv_current_time);
		tv_duration_time = (TextView) findViewById(R.id.tv_duration_time);
		
		bt_play.setOnClickListener(this);
		bt_pause.setOnClickListener(this);
		bt_re.setOnClickListener(this);
		
		//1.开启服务
		Intent intent = new Intent(this,MusicService.class);
		startService(intent);
		
		//2.绑定服务,获取中间人对象    
		myConn = new MyConn();
		bindService(intent, myConn, BIND_AUTO_CREATE);
		
		sb_seekm.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				//通过中间人对象  调用服务里面的方法  并根据当前拖动的位置   设置在那里播放
				iservice.callSeekToPosition(seekBar.getProgress());
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
	}

	//可以获取到服务中的中间人对象(MyBinder)
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iservice = (Iservice)service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
	
	/**
	 * 设置当前歌曲的时间信息
	 * @param duration   总时长
	 * @param currentPosition   当前时间
	 */
	private static void manageTime(int duration,int currentPosition){
		
		int durationMinute = duration/1000/60;
		int durationSecond = (duration/1000)%60;
		int currentMinute = currentPosition/1000/60;
		int currentSecond = currentPosition/1000%60;
		
		//设置当前时间
		tv_duration_time.setText(durationMinute+":"+durationSecond);
		tv_current_time.setText(currentMinute+":"+currentSecond);
	}
	
	@Override
	protected void onDestroy() {
		//最后解除绑定
		unbindService(myConn);
		super.onDestroy();
	}
	
	/**
	 * 播放音乐
	 */
	private void playMusic(){
		iservice.callPlayMusic();
	}
	
	/**
	 * 暂停音乐
	 */
	private void pauseMusic(){
		iservice.callPauseMusic();
	}
	
	/**
	 * 继续播放
	 */
	private void reMusic(){
		iservice.callReMusic();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_play:   //播放
			playMusic();
			break;
		case R.id.bt_pause:  //暂停
			pauseMusic();
			break;
		case R.id.bt_re:     //继续
			reMusic();
			break;
		default:
			break;
		}
	}

}
