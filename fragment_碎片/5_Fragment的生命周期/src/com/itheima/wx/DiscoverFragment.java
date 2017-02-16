package com.itheima.wx;

import com.itheima.life.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class DiscoverFragment extends Fragment {

	
	//������activity��
	@Override
	public void onAttach(Activity activity) {
		System.out.println("onAttach");
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
	}
	
	//Fragment ����һ������  ��ʾFragment������ 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discover, null);
		System.out.println("onCreateView");
		return view; 
	}

	//�����onCreateView�����г�ʼ����view  ��ȫ��ʼ�� 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		System.out.println("onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		System.out.println("onResume");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		System.out.println("onPause");
		super.onPause();
	}
	
	//�����治�ɼ�
	@Override
	public void onStop() {
		System.out.println("onStop");
		super.onStop();
	}
	
	//��oncreateView���������ʼ����view������
	@Override
	public void onDestroyView() {
		System.out.println("onDestroyView");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
	
	//ȡ������ 
	@Override
	public void onDetach() {
		
		System.out.println("onDetach");
		super.onDetach();
	}
	
	
	
}
