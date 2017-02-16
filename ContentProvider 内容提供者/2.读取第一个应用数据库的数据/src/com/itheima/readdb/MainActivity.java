package com.itheima.readdb;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_query;
	private Button bt_insert;
	private Button bt_delete;
	private Button bt_update;

	private Context mContext;
	private ContentResolver contentResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		bt_query = (Button) findViewById(R.id.bt_query);
		bt_insert = (Button) findViewById(R.id.bt_insert);
		bt_delete = (Button) findViewById(R.id.bt_delete);
		bt_update = (Button) findViewById(R.id.bt_update);

		bt_query.setOnClickListener(this);
		bt_insert.setOnClickListener(this);
		bt_delete.setOnClickListener(this);
		bt_update.setOnClickListener(this);

		contentResolver = mContext.getContentResolver();

	}

	// 从第一个应用程序查询数据
	private void queryFromFirstApp() {

		Uri uri = Uri.parse("content://com.itheima.provider/query");
		Cursor cursor = contentResolver.query(uri, new String[] { "name",
				"money" }, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			do {

				String name = cursor.getString(cursor.getColumnIndex("name"));
				String money = cursor.getString(cursor.getColumnIndex("money"));

				Log.d("xfhy", "程序二:  name:" + name + "  money:" + money);

			} while (cursor.moveToNext());
		} else {
			Toast.makeText(mContext, "未查询到数据", Toast.LENGTH_SHORT).show();
		}
	}

	// 往第一个应用程序插入数据
	private void insertToFirstApp() {

		Uri uri = Uri.parse("content://com.itheima.provider/insert");
		ContentValues values = new ContentValues();
		values.put("name", "张三丰");
		values.put("money", "1000");
		Uri insert = contentResolver.insert(uri, values);
		//Toast.makeText(mContext, insert.toString(), Toast.LENGTH_SHORT).show();
		Log.d("xfhy", ""+insert);
	}

	// 从第一个应用程序删除数据
	private void deleteFromFirstApp() {
		Uri uri = Uri.parse("content://com.itheima.provider/delete");
		int delete = contentResolver.delete(uri, "name=?", new String[]{"张三"});
		Toast.makeText(mContext, "删除"+delete+"行", Toast.LENGTH_SHORT).show();
	}

	// 从第一个应用程序更新数据
	private void updateFromFirstApp() {
		Uri uri = Uri.parse("content://com.itheima.provider/update");
		ContentValues values = new ContentValues();
		values.put("money", "5000");
		int update = contentResolver.update(uri, values, "name=?", new String[]{"张三丰"});
		Toast.makeText(mContext, "更新"+update+"行", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_query:
			queryFromFirstApp();
			break;
		case R.id.bt_insert:
			insertToFirstApp();
			break;
		case R.id.bt_delete:
			deleteFromFirstApp();
			break;
		case R.id.bt_update:
			updateFromFirstApp();
			break;
		default:
			break;
		}
	}

}
