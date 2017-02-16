package com.itheima.communication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 2017年2月11日
 * 
 * xfhy
 * 
 * TODO
 */
public class SecondFragment extends Fragment {
	private TextView tv_test;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, null);
		
		tv_test = (TextView) view.findViewById(R.id.tv_test);
		
		return view;
	}
	
	/**
	 * 设置TextView的值
	 * @param content
	 */
	public void setText(String content){
		tv_test.setText(content);
	}
	
}
