package com.itheima.rpcalc;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 2017年1月23日
 * 
 * XFHY
 * 
 * TODO
 */
public class ResultActivity extends Activity {
	private TextView tv_name;
	private TextView tv_sex;
	private TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		//1.找到控件
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_sex = (TextView) findViewById(R.id.tv_sex);
		tv_result = (TextView) findViewById(R.id.tv_result);
		
		//2.获取传递过来的数据
		//Return the intent that started this activity.    获取打开该activity的Intent对象
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String sex = intent.getStringExtra("sex");
		
		//3.将数据设置到控件上
		tv_name.setText("姓名: "+name);
		tv_sex.setText("性别: "+sex);
		
		byte[] bytes = null;
		
		try {
			switch (sex) {
			case "男":
				bytes = name.getBytes("gbk");
				break;
			case "女":
				bytes = name.getBytes("utf-8");
				break;
			case "人妖":
				bytes = name.getBytes("iso-8859-1");
				break;
			default:
				break;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//计算这个人的得分   并显示到控件上
		
		int score = 0;
		int number = 0;
		for (byte b : bytes) {
			number = b&0xff;
			score += number;
		}
		score = Math.abs(score%100);
		if (score >90) {
			tv_result.setText("您的人品非常好 您家的祖坟都冒青烟了");
		}else if (score >70) {
			tv_result.setText("有你这样的人品算是不错了..");
		}else if (score >60) {
			tv_result.setText("您的人品刚刚及格");
		}else{
			tv_result.setText("您的人品不及格....");
			
		}
	}
}
