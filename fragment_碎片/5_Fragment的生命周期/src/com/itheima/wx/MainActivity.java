package com.itheima.wx;

import com.itheima.life.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// [1]找到我们关心的控件
		Button btn_contact = (Button) findViewById(R.id.btn_contact);
		Button btn_discover = (Button) findViewById(R.id.btn_discover);
		Button btn_me = (Button) findViewById(R.id.btn_me);
		Button btn_wx = (Button) findViewById(R.id.btn_wx);

		// [2]给按钮设置点击事件
		btn_contact.setOnClickListener(this);
		btn_discover.setOnClickListener(this);
		btn_me.setOnClickListener(this);
		btn_wx.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		// [3]具体判断一下我点击的是哪个按钮
		
		//[4]获取fragment的管理者 
		FragmentManager fragmentManager = getFragmentManager();
		//[5]开启事物 
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.btn_wx: // 说明点击了微信按钮
			transaction.replace(R.id.ll, new WxFragment());
			break;

		case R.id.btn_contact: // 说明点击了微信按钮
			transaction.replace(R.id.ll, new ContactFragment());
			break;

		case R.id.btn_discover: // 说明点击了微信按钮
			transaction.replace(R.id.ll, new DiscoverFragment());
			break;

		case R.id.btn_me: // 说明点击了微信按钮
			transaction.replace(R.id.ll, new MeFragment());
			break;
		}
		//[6]最后一布 记得 提交事物 
		transaction.commit();

	}

}
