package com.xfhy.popupwindow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener,OnItemClickListener {

	private ImageButton ib_dropdown;
	private ListView listView;
	private EditText et_input;
	private List<String> datas;
	private PopupWindow popupWindow = null;
	private boolean isOpenPopupWindow = false;   //判断下拉框是否关闭

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ib_dropdown = (ImageButton) findViewById(R.id.ib_dropdown);
		et_input = (EditText) findViewById(R.id.et_input);
		ib_dropdown.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_dropdown:
			if(isOpenPopupWindow) {
				popupWindow.dismiss();   //如果下拉框是打开着的,则关闭
				isOpenPopupWindow = false;
			} else {
				showPopupWindow();   //显示下拉框
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 显示下拉选择框
	 */
	private void showPopupWindow() {
		
		initListView();   //初始化列表
		
		//将下拉列表显示在listView上  宽 ,高
		if(popupWindow == null) {
			popupWindow = new PopupWindow(listView, et_input.getWidth(), 300);  
		}
		popupWindow.setFocusable(true);   //设置可获取焦点
		popupWindow.setOutsideTouchable(true);   //设置外部可点击
		//设置背景
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.drawable.listview_background)));   //设置背景
		popupWindow.showAsDropDown(et_input, 0,-5);   //在什么控件的下面显示   x,y
		isOpenPopupWindow = true;
	}

	/**
	 * 初始化列表
	 */
	private void initListView() {
		listView = new ListView(this);
		listView.setDividerHeight(0);   //设置分割线的宽度
		listView.setBackgroundResource(R.drawable.listview_background);   //设置背景
		listView.setOnItemClickListener(this);
		
		datas = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			datas.add((1000+i)+"");
		}
		listView.setAdapter(new MyAdapter());
	}

	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return datas != null ? datas.size() : 0;   //判断是否为空
		}

		@Override
		public Object getItem(int position) {
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if(convertView == null) {
				view = View.inflate(parent.getContext(),R.layout.item_number , null);
			} else {
				view = convertView;
			}
			TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
			tv_number.setText(datas.get(position));
			return view;
		}
		
	}

	//listView点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String string = datas.get(position);
		et_input.setText(string);   //设置用户点击的值
		
		popupWindow.dismiss();   //关闭下拉框
	}
	
}
