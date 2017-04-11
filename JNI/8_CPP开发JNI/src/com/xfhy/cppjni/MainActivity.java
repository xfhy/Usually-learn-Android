package com.xfhy.cppjni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	static {
		System.loadLibrary("cpp");
	}
	
	private Button bt_test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_test = (Button) findViewById(R.id.bt_test);
		bt_test.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_test:
			Toast.makeText(this, helloFromCpp(), Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	public native String helloFromCpp();
	
}
