package com.itheima.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * @author XFHY
 * @date 2017年2月1日 下午7:44:50
 * @package com.itheima.db
 * @function
 */
public class AccoutProvider extends ContentProvider {

	// 1.定义一个路径匹配器
	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	// ctrl+shift+x 变大写
	private static final int QUERY_SUCCESS = 0;
	private static final int INSERT_SUCCESS = 1;
	private static final int UPDATE_SUCCESS = 2;
	private static final int DELETE_SUCCESS = 3;

	private MySqliteOpenHelper sqliteOpenHelper;

	// 2.创建一个静态代码块 在这个里面添加uri
	static {
		/**
		 * authority 注意: 和清单文件里面定义的一样 com.itheima.provider/query
		 */
		sURIMatcher.addURI("com.itheima.provider", "query", QUERY_SUCCESS);
		sURIMatcher.addURI("com.itheima.provider", "insert", INSERT_SUCCESS);
		sURIMatcher.addURI("com.itheima.provider", "update", UPDATE_SUCCESS);
		sURIMatcher.addURI("com.itheima.provider", "delete", DELETE_SUCCESS);
	}

	// 当内容提供者初始化 会执行此方法
	@Override
	public boolean onCreate() {
		// 3.初始化MySqliteOpenHelper对象,就可以获取SqlLiteDatabase对象,就可以操作数据库
		sqliteOpenHelper = new MySqliteOpenHelper(getContext()); // 内容提供者里有getContext()可以获取Context
		return false;
	}

	// 这个方法是对外暴露的
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// 匹配的代码
		int code = sURIMatcher.match(uri);
		// 匹配是 QUERY_SUCCESS
		if (code == QUERY_SUCCESS) {
			Log.d("xfhy", "匹配成功");
			
			//发送一条数据库被操作了的消息    通知内容观察者   数据库被操作了
			getContext().getContentResolver().notifyChange(uri, null);
			
			// 说明匹配成功了
			SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
			// 调用query方法
			Cursor cursor = db.query("info", projection, selection,
					selectionArgs, null, null, sortOrder);
//			db.close();
			// 小细节 ☆ 这个cursor不能关 关了,则返回回去也没用了
			return cursor;
		} else {
			// throw new IllegalArgumentException("哥们 :uri路径不匹配 请检测路径");
			return null;
		}
	}

	@Override
	public String getType(Uri uri) {
		/*
		 * switch (sURIMatcher.match(uri)) { case QUERY_SUCCESS: return
		 * "com.itheima.provider";
		 * 
		 * default: break; }
		 */
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		int code = sURIMatcher.match(uri);
		if (code == INSERT_SUCCESS) {
			
			SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
			// the row ID of the newly inserted row, or -1 if an error occurred
			long insert = db.insert("info", null, values);
			
			if(insert > 0){
				//发送一条数据库被操作了的消息    通知内容观察者   数据库被操作了
				getContext().getContentResolver().notifyChange(uri, null);
			}
			
//			db.close();
			Uri uri2 = Uri.parse("com.insert.success" + insert);
			return uri2;
		} else {
			Log.d("xfhy", "匹配insert失败");
			return null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		int code = sURIMatcher.match(uri);
		if (code == DELETE_SUCCESS) {
			
			SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
			// 返回受影响的行数
			int delete = db.delete("info", selection, selectionArgs);
			
			if(delete > 0){
				//发送一条数据库被操作了的消息    通知内容观察者   数据库被操作了
				getContext().getContentResolver().notifyChange(uri, null);
			}
			
//			db.close();
			return delete;
		} else {
			Log.d("xfhy", "匹配delete失败");
			return 0;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int code = sURIMatcher.match(uri);
		if (code == UPDATE_SUCCESS) {
			
			SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
			// 返回受影响的行数
			int update = db.update("info", values, selection, selectionArgs);
			
			if(update > 0){
				//发送一条数据库被操作了的消息    通知内容观察者   数据库被操作了
				getContext().getContentResolver().notifyChange(uri, null);
			}
			
//			db.close();
			return update;
		} else {
			Log.d("xfhy", "匹配update失败");
			return 0;
		}
	}

}
