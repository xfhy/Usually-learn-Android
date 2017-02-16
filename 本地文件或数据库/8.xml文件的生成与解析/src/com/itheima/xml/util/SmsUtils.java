package com.itheima.xml.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Xml;

import com.itheima.xml.bean.SmsBean;
import com.itheima.xml.dao.SmsDao;

/**
 * 2017年1月15日7:42:24
 * 
 * @author XFHY
 * 
 */
public class SmsUtils {

	// 备份的逻辑
	public static boolean backupSms(Context context) {
		// 0.获取短信数据
		ArrayList<SmsBean> arrayList = SmsDao.getAllSms();
		// 1.通过XMl获取一个XmlSerializer对象
		XmlSerializer xs = Xml.newSerializer();
		try {
			// 2.设置XmlSerializer的一些参数,比如设置xml写入到哪个文件中
			// os:xml文件写入流 encoding:流的编码
			xs.setOutput(context.openFileOutput("backupsms2.xml",
					Context.MODE_PRIVATE), "utf-8");
			// 3.序列化一个xml的声明头
			// encoding:xml文件的编码 standalone:是否独立
			xs.startDocument("utf-8", true);
			// 4.序列化一个根节点的开始节点
			// namespace:命名空间 name:标签的名称
			xs.startTag(null, "Smss");
			// 5.循环遍历list集合序列化一条条短信
			for (SmsBean smsBean : arrayList) {
				xs.startTag(null, "Sms");
				// name:属性的名称 value:属性值
				xs.attribute(null, "id", smsBean.id + "");

				xs.startTag(null, "num");
				// 写以后各标签的内容
				xs.text(smsBean.num);
				xs.endTag(null, "num");

				xs.startTag(null, "msg");
				xs.text(smsBean.msg);
				xs.endTag(null, "msg");

				xs.startTag(null, "date");
				xs.text(smsBean.date);
				xs.endTag(null, "date");

				xs.endTag(null, "Sms");
			}

			// 6.序列化一个根节点的结束节点
			xs.endTag(null, "Smss");

			// 7.将xml写入文件中,完成xml的序列化
			xs.endDocument();
			return true;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 解析xml文件读取短信内容
	public static int restoreSms(Context context) {

		ArrayList<SmsBean> arrayList = null;
		SmsBean smsBean = null;
		try {
			// 1.通过xml获取一个XmlPullParser对象
			XmlPullParser xpp = Xml.newPullParser();
			// 2.设置XmlPullParser对象的参数,需要解析的是哪个xml文件,设置一个文件读取流

			// 通过context获取一个资产管理者对象
			AssetManager assets = context.getAssets();
			// 通过资产管理者能获取一个文件读取流
			InputStream inputStream = assets.open("backupsms.xml");
			xpp.setInput(inputStream, "utf-8");

			// 3.获取当期那xml行的事件类型
			int type = xpp.getEventType();

			// 4.判断事件类型是否是文档结束的事件类型
			while (type != XmlPullParser.END_DOCUMENT) {
				// 如果不是,循环遍历解析每一行的数据.解析一行后,获取下一行的事件类型

				String currentTagName = xpp.getName();

				// 判断当前行的事件类型是开始标签还是结束标签
				switch (type) {
				case XmlPullParser.START_TAG:
					if(currentTagName.equals("Smss")){
						//如果当前标签是Smss,需要初始化一个集合
						arrayList = new ArrayList<SmsBean>();
					} else if(currentTagName.equals("Sms")){
						smsBean = new SmsBean();
						smsBean.id = Integer.valueOf(xpp.getAttributeValue(null,"id"));
						
					} else if(currentTagName.equals("num")){
						smsBean.num = xpp.nextText();
					}else if(currentTagName.equals("msg")){
						smsBean.msg =  xpp.nextText();
					}else if(currentTagName.equals("date")){
						smsBean.date =  xpp.nextText();
					}
					break;
				case XmlPullParser.END_TAG:
					//当前结束标签是Sms的话，一条短信数据封装完成， 可以加入list中
					if(currentTagName.equals("Sms")){
						arrayList.add(smsBean);
					}
					break;
				default:
					break;
				}
				type = xpp.next();//获取下一行的事件类型
			}
			
			return arrayList.size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
