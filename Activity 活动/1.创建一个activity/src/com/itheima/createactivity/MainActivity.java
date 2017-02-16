package com.itheima.createactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button bt_cal;
	private Button bt_testactivity;
	private Button bt_testactivity2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_cal = (Button)findViewById(R.id.bt_call);
		bt_testactivity = (Button)findViewById(R.id.bt_testactivity);
		bt_testactivity2 = (Button)findViewById(R.id.bt_testactivity2);
		
		
		bt_cal.setOnClickListener(this);
		bt_testactivity.setOnClickListener(this);
		bt_testactivity2.setOnClickListener(this);
	}

	/**
	 * 点击按钮 实现拨打电话的逻辑
	 */
	private void callPhone(){
		//1.创建一个意图对象    意图 :你想干一件什么事情
		Intent intent = new Intent();    
		//2.设置意图的action动作
		intent.setAction(Intent.ACTION_CALL);
		//3.设置数据  data
		intent.setData(Uri.parse("tel:"+112));
		//4.开启activity   记得加上call_phone的权限
		startActivity(intent);
	}
	
	/**
	 * 调用第二个界面
	 */
	private void callSecondActivity() {
		// [1]创建意图对象
		Intent intent = new Intent();
		// [2]设置意图的action(动作)
		intent.setAction("com.itheima.testactivity");
		// ☆☆☆☆☆注意 如果type 和 data同时使用的时候 应该用这个方法
		intent.setDataAndType(Uri.parse("itheima:" + 112), "aa/bb");
		// [4]开启activity 记得加上call_phone的权限
		startActivity(intent);
	}
	
	/**
	 * 调用第三个界面
	 */
	private void callThirdActivity(){
		Intent intent = new Intent(this, TestActivity2.class);
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_call:
			callPhone();
			break;
		case R.id.bt_testactivity:
			callSecondActivity();
			break;
		case R.id.bt_testactivity2:
			callThirdActivity();
			break;
		default:
			break;
		}
	}

}
