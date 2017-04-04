package com.xfhy.sliding;

import com.xfhy.sliding.ui.SlideMenu;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	private SlideMenu sm;
	private ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		sm = (SlideMenu) findViewById(R.id.sm);
		backButton = (ImageButton) findViewById(R.id.ib_back);
		
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_back:
			sm.switchState();  //切换侧滑菜单的开关状态
			break;

		default:
			break;
		}
	}

}
