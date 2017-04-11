package com.xfhy.callbackjava;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

	public JNI jni;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		jni = new JNI(this);

		Button bt_callbackvoid = (Button) findViewById(R.id.bt_callbackvoid);
		Button bt_callbackint = (Button) findViewById(R.id.bt_callbackint);
		Button bt_callbackstring = (Button) findViewById(R.id.bt_callbackstring);
		Button bt_callbackshowtoast = (Button) findViewById(R.id.bt_callbackshowtoast);
		bt_callbackvoid.setOnClickListener(this);
		bt_callbackint.setOnClickListener(this);
		bt_callbackstring.setOnClickListener(this);
		bt_callbackshowtoast.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_callbackvoid:
			jni.callbackvoidmethod();   //回调空方法
			break;
		case R.id.bt_callbackint:
			jni.callbackintmethod();
			break;
		case R.id.bt_callbackstring:
			jni.callbackStringmethod();
			break;
		case R.id.bt_callbackshowtoast:
			jni.callbackShowToast();
			break;
		default:
			break;
		}
	}


}
