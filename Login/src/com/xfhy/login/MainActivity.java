package com.xfhy.login;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et_account;
	private EditText et_password;
	private Button bt_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		et_account = (EditText) findViewById(R.id.et_account);
		et_password = (EditText) findViewById(R.id.et_password);
		bt_login = (Button) findViewById(R.id.bt_login);

		bt_login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String account = et_account.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
		}

		if (account.equals("admin") && password.equals("123")) {
			Toast.makeText(this, "登录成功!谢谢使用!再见!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
		}
	}

}
