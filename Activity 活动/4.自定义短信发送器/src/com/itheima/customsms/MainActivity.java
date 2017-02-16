package com.itheima.customsms;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	private EditText et_number;
	private Button bt_addcontact;
	private EditText et_sms_content;
	private Button bt_sendsms;
	private Button bt_sms_templet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_number = (EditText) findViewById(R.id.et_number);
		bt_addcontact = (Button) findViewById(R.id.bt_addcontact);
		et_sms_content = (EditText) findViewById(R.id.et_sms_content);
		bt_sendsms = (Button) findViewById(R.id.bt_sendsms);
		bt_sms_templet = (Button) findViewById(R.id.bt_sms_templet);
		
		
		
		bt_addcontact.setOnClickListener(this);
		bt_sendsms.setOnClickListener(this);
		bt_sms_templet.setOnClickListener(this);
	}

	/**
	 * 选择联系人
	 */
	private void addContact() {
		Intent intent = new Intent(this, ContactActivity.class);
		// ☆☆☆开启Activity有2种方式
		// (1)如果想要取开启的Activity的界面的数据 用 startActivityForResult();
		// (2)如果就是简简单单页面的跳转 就用startActivity()
		startActivityForResult(intent, 1); 
	}
	
	/**
	 * 发送短信
	 */
	private void sendSms() {

		// 1.获取联系人电话 获取 短信内容
		String number = et_number.getText().toString().trim();
		String content = et_sms_content.getText().toString().trim();

		// 2.获取SmsManager实例
		SmsManager smsManager = SmsManager.getDefault();
		// 2.1如果短信内容过多 发不出去 分条发送
		ArrayList<String> divideMessage = smsManager.divideMessage(content);
		for (String string : divideMessage) {
			// 3.发送短信数据
			smsManager.sendTextMessage(number, null, string, null, null);
		}
	}
	
	/**
	 * 选择短信模板
	 */
	private void chooseSmsTemp(){
		Intent intent = new Intent(this,SmsTempletActivity.class);
		startActivityForResult(intent, 2);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_addcontact:
			addContact();
			break;
		case R.id.bt_sendsms:
			sendSms();
			break;
		case R.id.bt_sms_templet:
			chooseSmsTemp();
			break;
		default:
			break;
		}
	}

	/**
	 * You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * 
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.    请求码
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().    另一个页面返回的码
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == 10) {
			//说明数据是由 ContactActivity返回 
			
			String phone = data.getStringExtra("number");
			et_number.setText(phone);
			
		}else if (resultCode == 12) {
			//说明数据是由SmsTemplateActivity返回 
			String smscontent = data.getStringExtra("sms_templet");
			et_sms_content.setText(smscontent);
			
		}
		
		/*// 如果是选择联系人界面返回的结果
		if (requestCode == 1 && resultCode == 10) {
			String phone = data.getStringExtra("number");
			et_number.setText(phone);
		}
		
		//如果是选择短信模板的界面返回的结果
		if(requestCode == 2 && resultCode == 12){
			String smsTemplet = data.getStringExtra("sms_templet");
			et_sms_content.setText(smsTemplet);
		}*/
		
		super.onActivityResult(requestCode, resultCode, data);
	}

}
