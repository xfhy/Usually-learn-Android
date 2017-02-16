package com.itheima.wechat;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_wx = (Button) findViewById(R.id.bt_wx);
		Button bt_contact = (Button) findViewById(R.id.bt_contact);
		Button bt_discover = (Button) findViewById(R.id.bt_discover);
		Button bt_me = (Button) findViewById(R.id.bt_me);

		bt_wx.setOnClickListener(this);
		bt_contact.setOnClickListener(this);
		bt_discover.setOnClickListener(this);
		bt_me.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		//1. 获取FragmentManager
		FragmentManager fragmentManager = getFragmentManager();
		
		//2. 开启事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.bt_wx:
			//3. 替换布局
			transaction.replace(R.id.ll_up, new WxFragment());
			break;
		case R.id.bt_contact:
			transaction.replace(R.id.ll_up, new ContactFragment());
			break;
		case R.id.bt_discover:
			transaction.replace(R.id.ll_up, new DiscoverFragment());
			break;
		case R.id.bt_me:
			transaction.replace(R.id.ll_up, new MeFragment());
			break;
		default:
			break;
		}
		
		//4. 记得提交   事务
		transaction.commit();
		
	}

}
