package com.itheima.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author  XFHY
 * @date  2017年2月1日 下午4:02:08
 * @package com.itheima.db
 * @function 
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {

	private final static String CREATE_INFO = "create table info("
			+ "_id integer primary key autoincrement,"
			+ "name varchar(20),"
			+ "money varchar(20))";
	
	public MySqliteOpenHelper(Context context){
		this(context, "Account.db", null, 1);
	}
	
	public MySqliteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, "Account.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_INFO);
		db.execSQL("insert into info(name,money) values(?,?)", new String[]{"张三","5000"});
		db.execSQL("insert into info(name,money) values(?,?)", new String[]{"李四","3000"});
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
