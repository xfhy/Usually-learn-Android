package com.xfhy.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xfhy.news.adapter.NewsAdapter;
import com.xfhy.news.bean.News;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	private List<News> newsList = new ArrayList<>();
	private NewsAdapter adapter;
	private ListView lv_news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
		initData();

		lv_news.setOnItemClickListener(this);
		lv_news.setOnItemLongClickListener(this);
	}

	/**
	 * 初始化界面
	 */
	private void initUI() {
		lv_news = (ListView) findViewById(R.id.lv_news);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			News news = new News();
			news.setTitle((i + 1) + ". 休杰克曼18岁到48岁的变化，印证着金刚狼的老去！");
			news.setContent(" “我这是我最后一次扮演这个角色，希望能做出一部不会被类型限定，不被评分定义，"
					+ "也不会被前作所限制的作品，只是想单纯拍一部好电影。”发布会上休·杰克曼言语中透露着依依惜别之意，"
					+ "“我看电影的时候有点儿紧张，而它超出了我的期待，有的时候还哭了，比如说我把X教授扛上台阶的时候。"
					+ "我爱这个角色，这个角色不会离开，会一直跟我在一起，这是我生命的一部分。”");
			news.setComments(random.nextInt(1000));
			newsList.add(news);
		}

		adapter = new NewsAdapter(newsList);
		lv_news.setAdapter(adapter);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position < newsList.size()) {
			Toast.makeText(this, "你点到我了,我在第" + (position + 1) + "个位置",
					Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("https://www.baidu.com/home/news/data/"
					+ "newspage?nid=16501074350934867313&n_type=0&p_from=1"));
			startActivity(intent);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		if (position < 0 || position > newsList.size()) {
			return false;
		}

		Toast.makeText(this, "你长按了第" + (position + 1) + "项", Toast.LENGTH_SHORT)
				.show();

		return true;
	}

}
