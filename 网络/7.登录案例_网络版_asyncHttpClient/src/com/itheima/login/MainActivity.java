package com.itheima.login;

import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima.login.net.HttpLoginUtils;
import com.itheima.login.util.UserInfoUtil;
import com.itheima.login_asynchttpclient.R;

/**
 * 2017年1月14日16:52:28
 * 
 * @author XFHY 登录练习
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText et_username;
	private EditText et_password;
	private CheckBox cb_reme;
	private Button btn_login;
	private boolean isrem;
	private String userpassword;
	private String username;

	private Context mContext; // 在Activity中定义一个属性private Context mContext;

	// 然后在setContentView之后将这个属性初始化(设置为当前的Activity.this),很多地方需要用到Context对象(比如Toast等),
	// 这样很方便.在公司,有经验的人都这样写,so...

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		// a.找到相应控件

		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		cb_reme = (CheckBox) findViewById(R.id.cb_reme);
		btn_login = (Button) findViewById(R.id.btn_login);

		// b.设置按钮的点击事件
		btn_login.setOnClickListener(this);

		// f.回显用户名密码
		Map<String, String> map = UserInfoUtil.readUserInfo(mContext); // 获取用户名和密码
		if (map != null) {
			et_username.setText(map.get("username")); // 设置用户名和密码
			et_password.setText(map.get("password"));

			cb_reme.setChecked(true); // 设置为记住密码
		}
	}

	/**
	 * 登录按钮执行逻辑
	 */
	private void login() {
		username = et_username.getText().toString().trim();
		userpassword = et_password.getText().toString().trim();
		isrem = cb_reme.isChecked();

		// d.判断用户名密码是否为空，不为空请求服务器（省略，默认请求成功）
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword)) {
			Toast.makeText(mContext, "用户名或密码为空", Toast.LENGTH_SHORT).show();
		}

		// 访问服务器,查看是否登录成功 通过handler进行返回 数据
//		Random random = new Random();
//		int nextInt = random.nextInt(10);
//		if (nextInt > 3) {
//			HttpLoginUtils.requestNetForGetLogin(mContext, username,
//					userpassword); // GET方式
//		} else {
			HttpLoginUtils.requestNetForPostLogin(mContext, username,
					userpassword); // POST方式
//		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			login();
			break;

		default:
			break;
		}
	}

}
