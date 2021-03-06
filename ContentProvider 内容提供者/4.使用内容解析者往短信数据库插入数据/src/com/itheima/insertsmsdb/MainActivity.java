package com.itheima.insertsmsdb;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	private EditText et_address;
	private EditText et_body;
	private Button bt_insert;

	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		et_address = (EditText) findViewById(R.id.et_address);
		et_body = (EditText) findViewById(R.id.et_body);
		bt_insert = (Button) findViewById(R.id.bt_insert);
		
		bt_insert.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//1.获取用户输入的短信发件人及短信内容
		String address = et_address.getText().toString().trim();
		String body = et_body.getText().toString().trim();
		
		//2.设置uri
		Uri uri = Uri.parse("content://sms/");   
		//3.封装需要插入的数据到ContentValues
		ContentValues values = new ContentValues();
		values.put("address", address);
		values.put("body", body);
		values.put("date", System.currentTimeMillis());
		//4.插入数据   到  短信数据库(需要权限)
		mContext.getContentResolver().insert(uri, values);
		
	}


}
