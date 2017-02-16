package com.itheima.insertcontactdb;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_insert;
	private EditText et_email;
	private EditText et_phone;
	private EditText et_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_email = (EditText) findViewById(R.id.et_email);
		bt_insert = (Button) findViewById(R.id.bt_insert);

		bt_insert.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// 1.获取用户输入的数据
		String name = et_name.getText().toString().trim();
		String phone = et_phone.getText().toString().trim();
		String email = et_email.getText().toString().trim();

		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)
				|| TextUtils.isEmpty(email)) {
			Toast.makeText(this, "不能留空", Toast.LENGTH_SHORT).show();
			return;
		}

		/*// 2.往raw_contact表中插入contact_id 这个id由数据的行数+1决定
		// 2.1 所以首先需要知道raw_contact有几行数据
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");

		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		int contact_id = count + 1;
		Log.d("xfhy", "contact_id:"+contact_id);

		// [3] 先往row_contact表 插入联系人的id (contact_id)
		ContentValues values = new ContentValues();
		values.put("contact_id", contact_id);
		getContentResolver().insert(uri, values);

		// [4]在把name phone email 插入到data表
		ContentValues nameValues = new ContentValues();
		nameValues.put("data2", name);
		nameValues.put("data1", name);
		// ☆ ☆ ☆ ☆ ☆ 插入的数据要告诉数据库 属于第几条联系人 和 数据类型
		nameValues.put("raw_contact_id", contact_id);
		nameValues.put("mimetype", "vnd.android.cursor.item/name");
		getContentResolver().insert(dataUri, nameValues);

		// [5]把phone号码 插入到data表
		ContentValues phoneValues = new ContentValues();
		phoneValues.put("data1", phone);
		phoneValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		phoneValues.put("data2", "2");
		phoneValues.put("raw_contact_id", contact_id);
		getContentResolver().insert(dataUri, phoneValues);

		// [6]把email 插入到data表
		ContentValues emailValues = new ContentValues();
		emailValues.put("data1", email);
		emailValues.put("mimetype", "vnd.android.cursor.item/email_v2");
		emailValues.put("data2", "2");
		emailValues.put("raw_contact_id", contact_id);
		getContentResolver().insert(dataUri, emailValues);*/
		
		
		 // 创建一个空的ContentValues  
        ContentValues values = new ContentValues();  
  
        // 向RawContacts.CONTENT_URI空值插入，      获取到插入行的contact_id
        // 先获取Android系统返回的rawContactId  
        // 后面要基于此id插入值  
        Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);   //  content://com.android.contacts/raw_contacts 
        long rawContactId = ContentUris.parseId(rawContactUri);  
        values.clear();  
  
             //raw_contact_id
        values.put(Data.RAW_CONTACT_ID, rawContactId);  
        // 内容类型     mimetype
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);    //vnd.android.cursor.item/name 
        // 联系人名字  
        values.put(StructuredName.GIVEN_NAME, name);    //data2
        // 向联系人URI添加联系人名字  
        getContentResolver().insert(Data.CONTENT_URI, values);  //com.android.contacts/data
        values.clear();  
  
        values.put(Data.RAW_CONTACT_ID, rawContactId);  
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);    //vnd.android.cursor.item/phone_v2
        // 联系人的电话号码  
        values.put(Phone.NUMBER, phone);    //data1
        // 电话类型  
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);   //data2    2
        // 向联系人电话号码URI添加电话号码  
        getContentResolver().insert(Data.CONTENT_URI, values);  
        values.clear();  
  
        values.put(Data.RAW_CONTACT_ID, rawContactId);  
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);  
        // 联系人的Email地址  
        values.put(Email.DATA, email);  
        // 电子邮件的类型  
        values.put(Email.TYPE, Email.TYPE_WORK);  
        // 向联系人Email URI添加Email数据  
        getContentResolver().insert(Data.CONTENT_URI, values);  
  
        Toast.makeText(this, "联系人数据添加成功", Toast.LENGTH_SHORT).show(); 

	}

}
