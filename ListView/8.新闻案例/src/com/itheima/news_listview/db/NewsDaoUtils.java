package com.itheima.news_listview.db;

import java.util.ArrayList;

import com.itheima.news_listview.bean.NewsBean;

import android.R.array;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 2017年1月19日13:17:30
 * 
 * @author XFHY
 * 
 *         数据库操作的工具类 这个类把一些常用的数据库操作封装起来,以方便后面使用
 */
public class NewsDaoUtils {

	/**
	 * 数据库名称
	 */
	public static final String DB_NAME = "news_database";
	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;

	private static NewsDaoUtils newsDaoUtils;

	private SQLiteDatabase db;

	/**
	 * 将构造方法私有化 如果要限制一个类对象产生，即：一个类只能有一个实例化对象，
	 * 一实例化对象肯定要调用构造方法，如果将构造方法藏起来，则外部肯定无法直接调用，就肯定不能用new关键字调用构造方法实例。
	 * 
	 * @param context
	 */
	private NewsDaoUtils(Context context) {
		// 实例化CoolWeatherOpenHelper对象,用来操作数据库
		NewsOpenHelper dbHelper = new NewsOpenHelper(context, DB_NAME, null,
				VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 获取NewsDaoUtils实例 synchronized : 这里是同步的一个事件只能有一个线程得到执行
	 * 
	 * @param context
	 * @return
	 */
	public synchronized static NewsDaoUtils getInstance(Context context) {
		if (newsDaoUtils == null) {// 当newsDaoUtils为null时就创建实例,new
			// 一个出来,这里可以访问自己的私有构造方法
			newsDaoUtils = new NewsDaoUtils(context);
		}
		return newsDaoUtils;
	}

	/**
	 * 获取所有的新闻 封装到list集合
	 * 
	 * @return
	 */
	public ArrayList<NewsBean> getNews() {
		ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();

		Cursor cursor = db.rawQuery("select * from News", null); // 查询数据库 获取所有新闻
		if (cursor != null && cursor.getCount() > 0) { // 判断里面是否有值

			while (cursor.moveToNext()) { // 向下一行移动
				NewsBean newsBean = new NewsBean(); // 这是一行的数据

				newsBean.id = cursor.getInt(cursor.getColumnIndex("_id"));
				newsBean.title = cursor.getString(cursor
						.getColumnIndex("title"));
				newsBean.des = cursor.getString(cursor.getColumnIndex("des"));
				newsBean.news_url = cursor.getString(cursor
						.getColumnIndex("news_url"));
				newsBean.comment = cursor.getInt(cursor
						.getColumnIndex("comment"));
				newsBean.type = cursor.getInt(cursor.getColumnIndex("type"));
				newsBean.time = cursor.getString(cursor.getColumnIndex("time"));
				newsBean.icon_url = cursor.getString(cursor
						.getColumnIndex("icon_url"));

				arrayList.add(newsBean);
			}
			

		}

		return arrayList;
	}

	/**
	 * 将新闻数据保存到数据
	 * @param arrayList
	 */
	public void saveNews(ArrayList<NewsBean> arrayList) {
		for (NewsBean newsBean : arrayList) {
			ContentValues values = new ContentValues();
			values.put("_id", newsBean.id);
			values.put("title", newsBean.title);
			values.put("des", newsBean.des);
			values.put("news_url", newsBean.news_url);
			values.put("comment", newsBean.comment);
			values.put("type", newsBean.type);
			values.put("time", newsBean.time);
			values.put("icon_url", newsBean.icon_url);
			
			db.insert("News", null, values);
		}
	}

	/**
	 * 删除数据库中缓存的旧数据
	 */
	public void deleteCache(){
		db.delete("News", null, null);
	}
	
}
