package com.xfhy.news.adapter;

import java.util.List;

import com.xfhy.news.R;
import com.xfhy.news.bean.News;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 2017年3月15日
 * 
 * xfhy
 * 
 * TODO
 */
public class NewsAdapter extends BaseAdapter {

	List<News> newsList = null;

	/**
	 * @param newsList
	 */
	public NewsAdapter(List<News> newsList) {
		this.newsList = newsList;
	}

	@Override
	public int getCount() {

		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public Object getItem(int position) {
		if (newsList.size() > position) {
			return newsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 优化 a.viewholder b.tag  c.convertView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		ViewHolder viewHolder = null;
		if(convertView == null){ //没有缓存   第一次进入
			view = View.inflate(parent.getContext(), R.layout.item_news_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.imageurl = (ImageView) view.findViewById(R.id.iv_icon);
			viewHolder.title = (TextView) view.findViewById(R.id.tv_title);
			viewHolder.content = (TextView) view.findViewById(R.id.tv_content);
			viewHolder.comments = (TextView) view.findViewById(R.id.tv_comments);
			
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.imageurl.setImageResource(R.drawable.icon);
		viewHolder.title.setText(newsList.get(position).getTitle());
		viewHolder.content.setText(newsList.get(position).getContent());
		viewHolder.comments.setText(newsList.get(position).getComments()+"");
		
		return view;
	}

	class ViewHolder {
		public ImageView imageurl;
		public TextView title;
		public TextView content;
		public TextView comments;
	}
	
}
