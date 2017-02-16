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

		// [1]�ҵ����ǹ��ĵĿؼ�
		Button btn_contact = (Button) findViewById(R.id.btn_contact);
		Button btn_discover = (Button) findViewById(R.id.btn_discover);
		Button btn_me = (Button) findViewById(R.id.btn_me);
		Button btn_wx = (Button) findViewById(R.id.btn_wx);

		// [2]����ť���õ���¼�
		btn_contact.setOnClickListener(this);
		btn_discover.setOnClickListener(this);
		btn_me.setOnClickListener(this);
		btn_wx.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		// [3]�����ж�һ���ҵ�������ĸ���ť
		
		//[4]��ȡfragment�Ĺ����� 
		FragmentManager fragmentManager = getFragmentManager();
		//[5]�������� 
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.btn_wx: // ˵�������΢�Ű�ť
			transaction.replace(R.id.ll, new WxFragment());
			break;

		case R.id.btn_contact: // ˵�������΢�Ű�ť
			transaction.replace(R.id.ll, new ContactFragment());
			break;

		case R.id.btn_discover: // ˵�������΢�Ű�ť
			transaction.replace(R.id.ll, new DiscoverFragment());
			break;

		case R.id.btn_me: // ˵�������΢�Ű�ť
			transaction.replace(R.id.ll, new MeFragment());
			break;
		}
		//[6]���һ�� �ǵ� �ύ���� 
		transaction.commit();

	}

}
