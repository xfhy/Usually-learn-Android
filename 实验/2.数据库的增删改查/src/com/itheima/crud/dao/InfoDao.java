package com.itheima.crud.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.crud.MySqliteOpenHelper;
import com.itheima.crud.bean.InfoBean;

/**
 * 2017年4月12日23:01:13
 * 
 * @author XFHY
 * 
 *         操作数据库的类
 * 
 */
public class InfoDao {

	private final static String INFO_TABLE = "info";
	private MySqliteOpenHelper mySqliteOpenHelper;
	
	public InfoDao(Context context){
		mySqliteOpenHelper = new MySqliteOpenHelper(context);
	}
	
	public boolean add(InfoBean infoBean) {
		
		SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
		//db.execSQL("insert into info(name,phone) values(?,?);", new Object[]{infoBean.name,infoBean.phone});
		
		ContentValues values = new ContentValues();    //将需要添加的数据库的值(一行)封装成ContentValues对象
		values.put("name", infoBean.name);
		values.put("phone", infoBean.phone);
		//参数:表名,nullColumnHack,values:数据的一行
		//返回值:代表刚刚添加的这个新行的id,-1代表添加失败
		long id = db.insert(INFO_TABLE, null, values);    //添加值到数据库    返回the row ID of the newly inserted row, or -1 if an error occurred
		
		db.close();   //关闭数据库
		
		if(id != -1){
			return true;
		}
		return false;
		
	}

	public boolean del(String name) {
		SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
		
		//参数:table:表名   whereClause:删除的条件    whereArgs:条件的占位符
		//返回值:成功删除多少行
		//public int delete (String table, String whereClause, String[] whereArgs)
		//delete from info where name='王五';
		int result = db.delete(INFO_TABLE, "name=?;", new String[]{name});
		
		db.close();   //关闭数据库
		
		if(result == 0){
			return false;
		}
		return true;
	}

	public int update(String name,String phone) {
		SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
		
		//参数:table:表名 values:更新的值  whereClause:更新的条件   whereArgs:更新条件的占位符
		//返回值 : 成功修改多少行
		//update(String table, ContentValues values, String whereClause, String[] whereArgs)
		//update info set phone='139999999'where name='王五';
		ContentValues values = new ContentValues();
		values.put("phone", phone);
		int result = db.update(INFO_TABLE, values, "name=?", new String[]{name});
		
		db.close();   //关闭数据库
		
		return result;
	}

	public ArrayList<InfoBean> query() {
		ArrayList<InfoBean> infoArrayList = new ArrayList<InfoBean>();
		
		SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
		
		//参数:表名,列名,where部分,为占位符指定具体的值,group by的列,对group by后的结果进一步约束,查询结果排序方式
		//查询Book表中的所有数据,并存到Cursor对象中
		//这种方式不能联合查询多个表
		Cursor cursor = db.query(INFO_TABLE, null, null, null, null, null, null, null);
		
		//moveToFirst()方法将数据的指针移动到第一行的位置,然后循环查询每一行的数据
		//通过Cursor的getColumnIndex()方法获取到某一列在表中对应位置索引
		//然后再将这个索引传入到相应的取值方法中,就可以得到从数据库中读取到的数据了
		//最后需要用close()方法关闭Cursor
		if(cursor.moveToFirst()){
			do {
				String id = cursor.getString(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String phone = cursor.getString(cursor.getColumnIndex("phone"));
				
				InfoBean infoBean = new InfoBean();
				infoBean.id = id;
				infoBean.name = name;
				infoBean.phone = phone;
				infoArrayList.add(infoBean);
				
			} while (cursor.moveToNext());
		}
		
		cursor.close();
		db.close();   //关闭数据库
		return infoArrayList;
	}

}
