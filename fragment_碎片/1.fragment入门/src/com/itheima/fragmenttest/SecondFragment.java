package com.itheima.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 2017年2月10日
 * 
 * xfhy
 * 
 * TODO
 */
public class SecondFragment extends Fragment {

	//当用户第一次画ui的时候调用  要显示Fragment自己的内容  setContentView(R.layout.activity_main);
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, null);
		return view;
	}
	
}
