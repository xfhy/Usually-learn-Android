package com.itheima.customsms;

import java.util.ArrayList;
import java.util.List;

import com.itheima.customsms.bean.Contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 2017年1月23日
 * 
 * XFHY
 * 
 * TODO
 */
public class ContactActivity extends Activity {
	private ListView lv_contact;

	private List<Contact> lists = new ArrayList<>();   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		lv_contact = (ListView) findViewById(R.id.lv_contact);
		
		//1.准备数据    自己想的
		for (int i = 0; i < 10; i++) {
			Contact contact = new Contact();
			contact.setName("张三"+i+"  ");
			contact.setNumber("184464867"+i);
			lists.add(contact);
		}
		
		//2.设置适配器
		lv_contact.setAdapter(new MyBaseAdapter());
		
		//3.设置listView点击事件
		lv_contact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//4.获取点击条目的电话数据
				String number = lists.get(position).getNumber();
				
				//5.将数据放到Intent对象中
				Intent intent = new Intent();
				intent.putExtra("number", number);
				//6.设置返回的验证码  和 数据
				setResult(10, intent);
				//7.关闭当前页面  
				finish();
			}
		});
		
	}
	
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return lists.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder viewHolder = null;
			if(convertView == null){
				view = View.inflate(getApplicationContext(), R.layout.item_contact_layout, null);
				viewHolder = new ViewHolder();
				viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
				viewHolder.tv_number = (TextView) view.findViewById(R.id.tv_number);
				view.setTag(viewHolder);
			} else {
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}
			
			//设置控件上的值
			viewHolder.tv_name.setText(lists.get(position).getName());
			viewHolder.tv_number.setText(lists.get(position).getNumber());
			
			return view;
		}
		
		class ViewHolder{
			TextView tv_name;
			TextView tv_number;
		}
		
	}
	
}
