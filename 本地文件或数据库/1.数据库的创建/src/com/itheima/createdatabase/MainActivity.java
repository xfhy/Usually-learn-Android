package com.itheima.createdatabase;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * 2017年1月15日13:24:18
 * 数据库创建练习
 * @author XFHY
 *
 */
public class MainActivity extends Activity {

	private Context mContext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mContext = this;
        
        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(mContext);
        SQLiteDatabase readableDatabase = mySqliteOpenHelper.getReadableDatabase();
    }

}
