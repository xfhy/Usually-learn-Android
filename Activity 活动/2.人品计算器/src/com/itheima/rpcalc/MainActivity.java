package com.itheima.rpcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private EditText et_name;
	private RadioGroup rg_sex;
	private Button bt_compute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_name = (EditText)findViewById(R.id.et_name);
		rg_sex = (RadioGroup)findViewById(R.id.rg_sex);
		bt_compute = (Button)findViewById(R.id.bt_compute);
		bt_compute.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		//[1]获取用户名
		String name = et_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Toast.makeText(getApplicationContext(), "请输入您的姓名", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		//[2]判断选中性别
		//获取用户选择的按钮id                 如果没有选中则返回-1
		int checkedRadioButtonId = rg_sex.getCheckedRadioButtonId();
		if(checkedRadioButtonId == -1){
			Toast.makeText(getApplicationContext(), "请选择您的性别", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		String sex= "";
		switch (checkedRadioButtonId) {
		case R.id.rb_male:
			sex = "男";
			break;
		case R.id.rb_female:
			sex = "女";
			break;
		case R.id.rb_other:
			sex = "人妖";
			break;
		default:
			break;
		}
		
		//启动第二个界面
		Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
		//传递数据
		intent.putExtra("name", name);
		intent.putExtra("sex", sex);
		startActivity(intent);
	}

	

}
