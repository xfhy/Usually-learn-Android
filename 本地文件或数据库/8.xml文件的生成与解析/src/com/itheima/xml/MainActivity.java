package com.itheima.xml;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.itheima.xml.util.SmsUtils;

/**
 * 短信的备份与恢复
 * 
 * @author XFHY 短信的数据是自己写的,不是从手机读取的
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button backupsms;
	private Button restoresms;

	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		
		backupsms = (Button) findViewById(R.id.backupsms);
		restoresms = (Button) findViewById(R.id.restoresms);

		backupsms.setOnClickListener(this);
		restoresms.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backupsms:
			if(SmsUtils.backupSms(mContext)){
				Toast.makeText(mContext, "短信备份成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(mContext, "短信备份失败", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.restoresms:
			int result = SmsUtils.restoreSms(mContext);
			Toast.makeText(mContext, "成功恢复"+result+"条短信", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

}
