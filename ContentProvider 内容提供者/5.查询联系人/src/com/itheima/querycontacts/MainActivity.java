package com.itheima.querycontacts;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.itheima.querycontacts.bean.Contact;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_query;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		bt_query = (Button) findViewById(R.id.bt_query);

		bt_query.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		List<Contact> contactLists = QueryContactUtils.getAllContacts(mContext);
		for (Contact contact : contactLists) {
			Log.d("xfhy", contact.toString());
		}
	}

}
