package com.itheima.news_listview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itheima.news_listview.adapter.NewsAdapter;
import com.itheima.news_listview.bean.NewsBean;
import com.itheima.news_listview.util.NewsUtil;

public class MainActivity extends Activity implements OnItemClickListener {

	private Context mContext;
	private ArrayList<NewsBean> arrayList;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ArrayList<NewsBean> arrayList = (ArrayList<NewsBean>)msg.obj;
			
			if(arrayList != null && arrayList.size() > 0){
				NewsAdapter newsAdapter = new NewsAdapter(mContext, arrayList);
				lv_news.setAdapter(newsAdapter);
			}
			
		};
	};
	private ListView lv_news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		lv_news = (ListView) findViewById(R.id.lv_news);

		//进来的时候,首先从数据库获取数据
		ArrayList<NewsBean> newsForDatabase = NewsUtil.getNewsForDatabase(mContext);
		if(newsForDatabase != null && newsForDatabase.size() > 0){
			Log.i("xfhy",newsForDatabase.toString());
			NewsAdapter newsAdapter = new NewsAdapter(mContext, newsForDatabase);
			lv_news.setAdapter(newsAdapter);
		}
		
		//再从服务器加载数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				arrayList = NewsUtil.getAllNewsForNetwork(mContext);

				// 创建一个Message对象
				Message msg = Message.obtain();
				msg.obj = arrayList;
				// 将Message对象发送到主线程
				handler.sendMessage(msg);

			}
		}).start();

		lv_news.setOnItemClickListener(this); // 设置监听器
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		NewsBean newsBean = (NewsBean) parent.getItemAtPosition(position);

		// 跳转浏览器
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(newsBean.news_url));
		startActivity(intent);
	}
}
