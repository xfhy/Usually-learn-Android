package com.xfhy.encode;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
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

	static {
		System.loadLibrary("encode");
	}
	
	private EditText et_text;
	private Button bt_encryption;
	private Button bt_decrypted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_text = (EditText) findViewById(R.id.et_text);
		bt_encryption = (Button) findViewById(R.id.bt_encryption);
		bt_decrypted = (Button) findViewById(R.id.bt_decrypted);
		bt_encryption.setOnClickListener(this);
		bt_decrypted.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_encryption: 
			//获取用户输入的字符串
			String string = et_text.getText().toString().trim();
			//调用C代码去加密该字符串
			String encryptionString = encryption(string);
			//将加密之后的字符串显示出来
			et_text.setText(encryptionString);
			break;
		case R.id.bt_decrypted:
			String string2 = et_text.getText().toString().trim();
			//调用C代码去解密该字符串
			String decryptedString = decrypted(string2);
			//将解密之后的字符串显示出来
			et_text.setText(decryptedString);
			break;
		default:
			break;
		}
	}

	/**
	 * 加密
	 * @return
	 */
	public native String encryption(String text);
	
	/**
	 * 解密
	 * @return
	 */
	public native String decrypted(String text);
	
}
