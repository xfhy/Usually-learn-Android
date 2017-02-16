package com.itheima.customsms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 2017年1月24日
 * 
 * XFHY
 * 
 * TODO
 */
public class SmsTempletActivity extends Activity {
	private ListView lv_sms_templet;
	String objects[] = {"我在开会,请稍后联系","我在吃饭,请稍后联系","我在打代码,请稍后联系","我在开车,请稍后联系","我在约会,请稍后联系"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_templet);
		//1.找到控件
		lv_sms_templet = (ListView) findViewById(R.id.lv_sms_templet);
		//2.准备适配器
		ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_smstep_layout, objects);
		//3.设置适配器
		lv_sms_templet.setAdapter(adapter);
		
		//4.设置listView点击事件   监听器
		lv_sms_templet.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//5.获取点击条目的数据
				String smsTemplet = objects[position];
				//6.将数据放到Intent对象中
				Intent intent = new Intent();
				intent.putExtra("sms_templet", smsTemplet);
				//7.Call this to set the result that your activity will return to its caller.    
				//将数据返回给这个页面的调用者
				setResult(12, intent);
				//8.销毁当前页面    调用这个界面的Activity的onActivityResult()方法将被调用
				/*
				 * Call this when your activity is done and should be closed.
				 * The ActivityResult is propagated back to whoever launched you
				 * via onActivityResult().
				 */
				finish();
			}
		});
		
	}
}
