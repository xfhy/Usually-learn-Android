package com.itheima.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "info.db";
	private static final int DB_VERSION = 1;
	
	public static final String DB_TABLE = "peopleinfo";
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PHONE = "phone";
	
	
	private static final String DB_CREATE = "create table " + DB_TABLE
			+ " (" + KEY_ID + " integer primary key autoincrement, "
			+ KEY_NAME + " varchar(20), " + KEY_PHONE + " varchar(11))";
	
	public MySqliteOpenHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	//oncreate方法是第一次创建表的时候会被调用   特别适合做表结构的初始化
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone"
				+ " varchar(11))");*/
		db.execSQL(DB_CREATE);
	}

	//onUpgrade数据库版本号发生改变时调用      适合做表结构的更改
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
	}

}
