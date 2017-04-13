package com.itheima.crud;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.xfhy.crud.R;
import com.itheima.crud.adapter.InfoAdapter;
import com.itheima.crud.bean.InfoBean;
import com.itheima.crud.dao.InfoDao;

/**
 * 2017年4月12日23:00:48 数据库创建练习
 * 
 * @author XFHY
 * 
 * 1.创建适配器InfoAdapter
 * 2.查询数据库,将数据封装到list集合中
 * 3.将集合放到Adapter中
 * 4.设置adapter
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private Context mContext;

	private ListView listView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(mContext);
		SQLiteDatabase readableDatabase = mySqliteOpenHelper
				.getReadableDatabase();

		findViewById(R.id.btn_add).setOnClickListener(this);
		findViewById(R.id.btn_del).setOnClickListener(this);
		findViewById(R.id.btn_update).setOnClickListener(this);
		findViewById(R.id.btn_query).setOnClickListener(this);

		listView = (ListView) findViewById(R.id.lv_data);
	}

	@Override
	public void onClick(View v) {
		InfoDao infoDao = new InfoDao(mContext);
		switch (v.getId()) {
		case R.id.btn_add:
			InfoBean infoBean = new InfoBean();
			infoBean.name = "张三";
			infoBean.phone = "14653213546";
			infoDao.add(infoBean);
			break;
		case R.id.btn_del:
			infoDao.del("张三");
			break;
		case R.id.btn_update:
			infoDao.update("张三","789456132");
			break;
		case R.id.btn_query:
			ArrayList<InfoBean> arrayList = infoDao.query();
			InfoAdapter infoAdapter = new InfoAdapter(arrayList,mContext);
			listView.setAdapter(infoAdapter);
			break;

		default:
			break;
		}
	}

}
