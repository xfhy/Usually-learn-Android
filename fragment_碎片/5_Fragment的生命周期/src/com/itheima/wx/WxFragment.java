package com.itheima.wx;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.itheima.life.R;

public class WxFragment extends Fragment {

	//依附在Activity上
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("xfhy", "onCreate");
		super.onCreate(savedInstanceState);
	}
	
	//fragment加载一个布局,显示Fragment的内容
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_wx, null);
		view.findViewById(R.id.btn_test).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("哈哈哈 我被点击了");
			}
		});
		
		return view; 
	}
	
	//在这个OnCreateView方法中初始化的view,完全初始化
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d("xfhy", "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Log.d("xfhy", "onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.d("xfhy", "onResume");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		Log.d("xfhy", "onPause");
		super.onPause();
	}
	
	//当界面不见了
	@Override
	public void onStop() {
		Log.d("xfhy", "onStop");
		super.onStop();
	}
	
	//当在OnCreateView方法里面的view销毁了
	@Override
	public void onDestroyView() {
		Log.d("xfhy", "onDestroyView");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.d("xfhy", "onDestroy");
		super.onDestroy();
	}
	
	//取消依附51052119460228423x
	@Override
	public void onDetach() {
		Log.d("xfhy", "onDetach");
		super.onDetach();
	}
	
}
