package com.itheima.sendrice;

import com.itheima.sendrice.receive.FinalReceiver;

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
import android.view.View.OnClickListener;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.bt_sendrice).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		//发送一条有序广播
		Intent intent = new Intent();
		//设置一个action
		intent.setAction("com.itheima.rice");
		
		/*
		 * Parameters
			intent 所有匹配这个意图的都将接收到这条广播
			receiverPermission 	接收的权限
			resultReceiver 	最终的接收者
			scheduler 	A custom Handler with which to schedule the resultReceiver callback; if null it will be scheduled in the Context's main thread.
			initialCode 	An initial value for the result code. Often Activity.RESULT_OK.
			initialData 	An initial value for the result data. Often null.
			initialExtras 	An initial value for the result extras. Often null.
		 * */
		sendOrderedBroadcast(intent, null, new FinalReceiver(), null, Activity.RESULT_OK, "习大大给每个村民发了1000斤大米", null);
	}


}
