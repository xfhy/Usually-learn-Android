package com.itheima.news_listview.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 2017年1月19日13:55:35
 * 
 * @author XFHY
 *
 * 数据库的创建以及更新
 *
 */
public class NewsOpenHelper extends SQLiteOpenHelper {

	/**
	 * 新闻 News  建表语句
	 * 
	 */
	public static final String CREATE_NEWS = "create table News ("
			+ "_id integer primary key, "
			+ "title text, "
			+ "des text, "
			+ "news_url text, "
			+ "comment integer, "
			+ "type integer, "
			+ "time text, "
			+ "icon_url text)";
	
	public NewsOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_NEWS);    //建立新闻表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
