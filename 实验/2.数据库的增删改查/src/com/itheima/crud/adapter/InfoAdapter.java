package com.itheima.crud.adapter;

import java.util.ArrayList;

import com.xfhy.crud.R;
import com.itheima.crud.bean.InfoBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {

	
	
	private ArrayList<InfoBean> arrayList;
	private Context context;

	public InfoAdapter(ArrayList<InfoBean> arrayList, Context mContext) {
		this.arrayList = arrayList;
		this.context = mContext;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//1.获取那个item上的数据
		InfoBean infoBean = arrayList.get(position);
		View view = null;
		ViewHolder viewHolder = null;
		
		//2.优化listview    恢复view
		if(convertView != null){
			view = convertView;  //恢复缓存的view
			viewHolder = (ViewHolder)view.getTag();   //获取缓存的数据
		} else {
			//3.加载布局
			view = View.inflate(context, R.layout.item_people_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.id = (TextView)view.findViewById(R.id.tv_id);
			viewHolder.name = (TextView)view.findViewById(R.id.tv_name);
			viewHolder.phone = (TextView)view.findViewById(R.id.tv_phone);
			
			//将当前的viewHolder缓存到view中
			view.setTag(viewHolder);
		}
		 
		viewHolder.id.setText(infoBean.id);
		viewHolder.name.setText(infoBean.name);
		viewHolder.phone.setText(infoBean.phone);
		
		return view;
	}

	//用来缓存数据的
	class ViewHolder{
		TextView id;
		TextView name;
		TextView phone;
		
	}
	
}
