package com.itheima.ipdail;

import com.itheima.ipdail.util.SharedUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	private EditText et_number;
	private Button bt_save;

	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		
		et_number = (EditText) findViewById(R.id.et_number);
		bt_save = (Button) findViewById(R.id.bt_save);
		
		bt_save.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		String number = et_number.getText().toString().trim();
		SharedUtils.saveNumber(mContext, number);
	}

}
