package com.itheima.xml.dao;

import java.util.ArrayList;

import com.itheima.xml.bean.SmsBean;

/**
 * 2017年1月15日7:43:20
 * 数据的增删改查
 * @author XFHY
 *
 */
public class SmsDao {
	
	//获取所有短信(这里的短信是自己写的)
	public static ArrayList<SmsBean> getAllSms(){
		ArrayList<SmsBean> arrayList = new ArrayList<SmsBean>();
		
		SmsBean smsBean = new SmsBean();
		smsBean.id = 1000;
		smsBean.num = "110";
		smsBean.msg = "qwer";
		smsBean.date = "2017年1月15日7:49:51";
		
		SmsBean smsBean1 = new SmsBean();
		smsBean1.id = 1001;
		smsBean1.num = "120";
		smsBean1.msg = "qwert";
		smsBean1.date = "2017年1月15日7:49:51";
		
		SmsBean smsBean2 = new SmsBean();
		smsBean2.id = 1002;
		smsBean2.num = "119";
		smsBean2.msg = "qwerty";
		smsBean2.date = "2017年1月15日7:49:51";
		
		arrayList.add(smsBean);
		arrayList.add(smsBean1);
		arrayList.add(smsBean2);
		
		return arrayList;
	}
	
}
