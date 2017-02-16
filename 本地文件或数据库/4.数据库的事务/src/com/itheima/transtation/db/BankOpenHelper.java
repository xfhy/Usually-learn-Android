package com.itheima.transtation.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BankOpenHelper extends SQLiteOpenHelper {

	private final static String CREATE_ACCOUT = "create table accout ("
			+ "_id integer primary key autoincrement,"
			+ "name varchar(11),"
			+ "money integer)";
	
	public BankOpenHelper(Context context){
		super(context, "accout", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ACCOUT);   //执行sql建表语句
		db.execSQL("insert into accout(name,money) values('张三',2000)");
		db.execSQL("insert into accout(name,money) values('李四',5000)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
