package com.itheima.news_listview.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.news_listview.R;
import com.itheima.news_listview.bean.NewsBean;
import com.itheima.news_listview.view.MyImageView;

/**
 * 2017年1月19日14:13:18
 * @author XFHY
 * 
 * ListView的适配器
 * 
 */
public class NewsAdapter extends BaseAdapter {

	private ArrayList<NewsBean> list;
	private Context context;

	public NewsAdapter(Context context,ArrayList<NewsBean> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//1.获取position位置条目对应的list集合中的新闻,Bean对象
		NewsBean newsBean = list.get(position);
		
		View view = null;
		
		ViewHolder viewHolder = null;   //用来缓存view上的数据
		
		//2.复用convertView优化listview,创建一个view作为getview的返回值用来显示一个条目
		if(convertView != null){
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();   //将之前缓存的数据  恢复
		} else {
			//加载布局     
			
			//方式1
//			view = View.inflate(context, R.layout.item_news_layout, null);
			//方式2
			view = LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);
			//方式3
//			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			view = layoutInflater.inflate(R.layout.item_news_layout, null);
			
			
			viewHolder = new ViewHolder();
			
			//3.获取view上的子控件   存到  viewHolder里面
			viewHolder.item_img_icon = (MyImageView)view.findViewById(R.id.item_img_icon);
			viewHolder.item_tv_title = (TextView)view.findViewById(R.id.item_tv_title);
			viewHolder.item_tv_des = (TextView)view.findViewById(R.id.item_tv_des);
			viewHolder.item_tv_type = (TextView)view.findViewById(R.id.item_tv_type);
			viewHolder.item_tv_comment = (TextView)view.findViewById(R.id.item_tv_comment);
			
			view.setTag(viewHolder);   //将这个内部类(缓存数据的类)存到view里面
			
		}
		//4.将数据设置给这些子控件显示
		 //view子控件的view对象在ViewHolder里面,直接可以设置里面的数据,就等于在设置view里面的子控件的数据
		viewHolder.item_img_icon.setImageUrl(newsBean.icon_url);   //通过图片的url即可显示当前图片(通过网络)
		viewHolder.item_tv_title.setText(newsBean.title);
		viewHolder.item_tv_des.setText(newsBean.des);
		viewHolder.item_tv_comment.setText("评论: "+newsBean.comment);
		
		
		switch (newsBean.type) {
		case 0:
			viewHolder.item_tv_type.setText("娱乐");
			break;
		case 1:
			viewHolder.item_tv_type.setText("科技");
			break;
		case 2:
			viewHolder.item_tv_type.setText("社会");
			break;
		case 3:
			viewHolder.item_tv_type.setText("八卦");
			break;
		default:
			viewHolder.item_tv_type.setText("娱乐");
			break;
		}
		
		return view;
	}

	//用来缓存 item条目上的所有的控件对象
	class ViewHolder{
		MyImageView item_img_icon;
		TextView item_tv_title;
		TextView item_tv_des;
		TextView item_tv_comment;
		TextView item_tv_type;
	}
	
}
