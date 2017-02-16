package com.itheima.transtation;

import com.itheima.transtation.db.BankOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 2017年1月15日21:14:57
 * @author XFHY
 *
 * 数据库的事务    练习
 * 
 * 要么全部成功,要么全部失败.
 * 
 * 典型案例:银行转账
 * 
 */
public class MainActivity extends Activity {

	private Context mcContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mcContext = this;
		
		Button btn = (Button)findViewById(R.id.btn_tran);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				BankOpenHelper bankOpenHelper = new BankOpenHelper(mcContext);
				SQLiteDatabase db = bankOpenHelper.getReadableDatabase();
				
				/*
				 * 标准事务  模板
				 *   db.beginTransaction();
					   try {
					     ...
					     db.setTransactionSuccessful();
					   } finally {
					     db.endTransaction();
					   }

				 * */
				
				//开启一个数据库事务
				db.beginTransaction();
				try {
					db.execSQL("update accout set money=money-200 where name=?",new String[]{"李四"});
					
					int i = 100/0;   //模拟一个异常
					
					db.execSQL("update accout set money=money+200 where name=?",new String[]{"张三"});
					
					//标记事务中的sql语句全部执行成功
					db.setTransactionSuccessful();
				} finally{
					//判断事务的标记是否成功  如果不成功,回滚错误之前执行的sql语句
					db.endTransaction();    
				}
				
				
			}
		});
	}

}
