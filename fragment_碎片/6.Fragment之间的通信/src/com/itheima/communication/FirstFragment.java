package com.itheima.communication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 2017年2月11日
 * 
 * xfhy
 * 
 * TODO
 */
public class FirstFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_first, null);
		
		Button bt_update = (Button) view.findViewById(R.id.bt_update);
		bt_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Fragment通信的桥梁是Activity
				
				//1. 获取这个Fragment依附的Activity,再通过这个Activity获取FragmentManager
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				//2. FragmentManager可以通过findFragmentByTag找到SecondFragment
				SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("f2");
				secondFragment.setText("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
			}
		});
		
		return view;
	}
}
