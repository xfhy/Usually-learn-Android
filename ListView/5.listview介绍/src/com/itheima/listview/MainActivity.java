package com.itheima.listview;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 2017年1月17日17:46:57
 * @author XFHY
 * 
 * ListView的简单介绍
 * 
 */
public class MainActivity extends Activity {

	private Context mcContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mcContext = this;
		
		ListView listView = (ListView)findViewById(R.id.lv_simple);
		MyListAdapter myListAdapter = new MyListAdapter();
		listView.setAdapter(myListAdapter);
	}

	class MyListAdapter extends BaseAdapter{   //公司一般用BaseAdapter作为适配器

		//用来存放   一共创建了多少个对象    列表上   共创建了多少个对象
		private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		//告诉list要显示多少个条目
		@Override
		public int getCount() {
			return 40;
		}

		//根据position获取listview上条目对应的Bean数据,该方法不影响数据的显示,可以先不实现
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		//用来获取条目position行的id,该方法不影响数据的显示,可以先不实现
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		//返回一个View对象作为条目上的内容展示,必须实现.该方法返回什么样的view,listView的条目上就显示什么样的view
		//屏幕上每显示一个条目getview方法就会被调用一次   convertView:曾经使用过的内存对象,可以被重复使用,使用前需要判断.
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = null; //
			
			if(convertView != null){
				textView = (TextView) convertView;
			} else {
				textView = new TextView(mcContext);
				
				
				map.put(textView.hashCode(), 1);
			}
			textView.setText("position:"+position);
			textView.setTextSize(30);
			System.out.println("创建了"+map.size()+"个Bean对象");
			
			return textView;
		}
		
	}
	
	
}
