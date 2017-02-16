package com.itheima.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

	public MySqliteOpenHelper(Context context){
		//android 4.0之后只能升不能降
		super(context, "info.db", null, 2);
	}
	
	//oncreate方法是第一次创建表的时候会被调用   特别适合做表结构的初始化
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20))");
	}

	//onUpgrade数据库版本号发生改变时调用      适合做表结构的更改
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		//添加一个phone字段
		db.execSQL("alter table info add phone varchar(11)");
	}

}
