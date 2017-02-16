package com.itheima.sms;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	//0.准备数据
	String[] objects = {"我用一生时间来把你照顾，漫漫长路有你相伴不再孤独。我已习惯牵你的手散步，你手心里有我甜蜜的温度，风风雨雨我都会陪在你身边，疼你爱你珍惜你直到永远！"
			,"清风吹送着我的思念，炽热的情感令我辗转难眠；细雨诉说着我的爱恋，幸福的爱情滋润着我的心田；青山表达着我的信念，爱你绝不改变；鲜花绽放着笑颜，祝福我们不朽的爱。亲爱的，我要牵着你的手，走遍天涯海角，让世界见证我们的爱！"
			,"你我相知相伴走过四季走过冬天，即使天涯相隔也不会遥远；你我相依相偎跨越山河跨越险滩，即便风雨兼程也会星光灿烂；你我相亲相爱放飞梦想放飞心愿，不管是山高水远路不平坦我也会陪伴在你每一个放逐心情的瞬间！"
			," 因为你太过于热情，所以总觉得别人对你都太冷漠，因为你太爱一个人，所以别人一个疏忽你都觉得那是不爱你了。多把精力放在自己身上，你会减少很多坏情绪。"
			," 有一种爱，会在不经意间刻骨；有一种相遇，会在天意安排下完成；有一个人，会让另一个人与幸福相随。感恩那种刻骨的爱情，它让人懂得了爱情的宝贵，同时也清楚了爱情的甜蜜。"
			," 一辈子不结婚是挺可怕的，更可怕的是，有些人他一辈子在婚姻里，可从来没有得到过爱情，所以，趁着年轻，勇敢的去爱一次，留下美好的回忆吧！"
			," 努力经营自己生活中的点滴美好，告诉自己不要低头，即使人生多有艰辛，世事无常。我们总是想要学会珍惜，却改不了臭脾气。适合的人，像孩子一样，真诚；像夕阳一样，温暖。像天空一样，宁静。"
			," 你的目光是锐利的骄阳，穿透我幽暗的生命，给予我新生的希望；你的关怀是温暖的气场，让我重新快乐的起航，不会再孤单彷徨！无论海角天涯，我的心永远在你身旁。"
			," 一个微笑，包含多少温暖；一个祝福，藏纳多少祝福；一声问候，代表多少关爱；一句你好吗？是我对你无限的想念，愿你轻轻松松、幸福快乐每一秒！"
			,"长长的距离长长的线，长长的想你长长的盼，长长的时间长长的情，长长的梦里长长的见，长长的联系长长的缘，长长的祝福长长的甜，长长的情缘爱你到永远！"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//1.找到控件
		ListView lv_sms = (ListView) findViewById(R.id.lv_sms);
		
		//2.创建适配器
		ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_sms_layout, objects);
		
		//3.设置适配器
		lv_sms.setAdapter(adapter);
		
		//4.设置条目的监听
		lv_sms.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//5.获取选中的短信内容
				String smsContent = objects[position];
				System.out.println(smsContent);
				
				//6.跳转到发送短信的界面    用隐式意图
				
				/*
				 *  
				 *    这里是查看源码看到的.ui.ComposeMessageActivity   这个类
				 * <intent-filter>
	               <action android:name="android.intent.action.SEND" />
	               <category android:name="android.intent.category.DEFAULT" />
	               <data android:mimeType="text/plain" />
	           	</intent-filter>
				 * */
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);   //android.intent.action.VIEW
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.setType("text/plain");
				
				//7.将数据传到短信发送界面
				intent.putExtra("sms_body", smsContent);
				
				//8.启动意图
				startActivity(intent);
				
				
			}
			
		});
		
	}

}
