package com.itheima.registerbroadcast;

import com.itheima.registerbroadcast.service.ScreenService;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//开启服务
		Intent intent = new Intent(this,ScreenService.class);
		//这种方式开启的服务是长期运行在后台的,即使Activity已经消耗,服务也还在
		startService(intent);   
		
		
	}

}
