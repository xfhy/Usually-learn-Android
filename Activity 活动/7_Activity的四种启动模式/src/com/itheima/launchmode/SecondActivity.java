package com.itheima.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	
	
	//������һ��ҳ�� 
		public void click1(View v){
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			
		}
		//�����ڶ���ҳ�� 
		public void click2(View v){
			Intent intent = new Intent(this,SecondActivity.class);
			startActivity(intent);
		}
	
}
