package com.itheima.news_listview.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.GetChars;

import com.itheima.news_listview.bean.NewsBean;
import com.itheima.news_listview.db.NewsDaoUtils;

/**
 * 2017年1月19日14:14:15
 * @author XFHY
 * 
 * 一些公共的操作方法
 * 
 */
public class NewsUtil {

	// 这里不能用主机名,而应该用主机的ip地址
	public static String newsPath_url = "http://192.168.191.1:8080/itheima74/servlet/GetNewsServlet";

	// 从服务器获取新闻的数据,解析json,并放 到 list中返回
	public static ArrayList<NewsBean> getAllNewsForNetwork(Context context) {

		ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();

		// 请求服务器 获取新闻数据
		try {
			// 1.获取url连接
			URL url = new URL(newsPath_url);
			// 2.获取HttpURLConnection对象'
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 3.设置访问的一些参数
			connection.setRequestMethod("GET"); // 设置请求方式
			connection.setReadTimeout(5000); // 设置超时时间
			// 4.获取请求响应码
			int code = connection.getResponseCode();
			if (code == 200) {
				//5.获取请求到的流对象
				InputStream inputStream = connection.getInputStream();
				String result = StreamUtils.streamToString(inputStream);
				
				/*
				 * {
						newss: [
						{
							id: 2,
							time: "",
							des: "今天的世界，很大程度上是以群体思维哲学为基础的，但在团队合作中个体的独立性和创造性也不容忽视。为了在群体中留下独特的印记，我们必须在为集体着想和独立思考之间寻找平衡点。许多有影响力的领导人、发明家、商人和女性都是那些重视独处的人，在独自思考的时候是也是他们最有创造力的时候。",
							title: "如何在“为集体着想”和“独立思考”之间找到平衡？",
							news_url: "http://m.jiemian.com/article/1045279.html",
							icon_url: "http://img1.jiemian.com/101/original/20161231/148315045528586800_a640x364.jpg",
							comment: 5000,
							type: 1
						},
						{
							id: 1,
							time: "",
							des: "苹果iPhone6s发布的具体时间越传越近了，但至今还没有官方的准信儿。今天还是让我们关注一下火狐漏洞吧。火狐浏览器开发商Mozilla提醒用户立即升级到最新版本，最好还要修改密码和登录信息。",
							title: "苹果或于9月9日发布iPhone6s 火狐爆严重漏洞",
							news_url: "http://m.jiemian.com/article/347958.html",
							icon_url: "http://img.jiemian.com/101/original/20150808/143899303035536900_a580x330.jpg",
							comment: 1200,
							type: 3
						},
						{
							id: 0,
							time: "",
							des: "凤凰科技讯 8月8日消息，今天有网友爆料，京东创始人兼CEO刘强东已与奶茶妹妹章泽天在朝阳区民政局领证结婚。",
							title: "刘强东与奶茶妹妹领证结婚 有图为证",
							news_url: "http://i.ifeng.com/news/sharenews.f?aid=100435430",
							icon_url: "http://d.ifengimg.com/mw604/y3.ifengimg.com/ifengimcp/pic/20150808/ce1b80056cfc584fafbf_size20_w450_h800.jpg",
							comment: 3000,
							type: 2
						}
					]
				}
				 * */
				
				// 解析获取的新闻到list集合中
				JSONObject root_json = new JSONObject(result);
				JSONArray all_news_json = root_json.getJSONArray("newss");
				for (int i = 0; i < all_news_json.length(); i++) {
					
					//这是一个新闻的对象   里面包含一条新闻
					JSONObject news = (JSONObject) all_news_json.get(i);
					
					//创建一个NewsBean新闻对象
					NewsBean newsBean = new NewsBean();  
					
					//从这个JSONObject中解析数据到   新闻对象中
					newsBean.id = news.getInt("id");
					newsBean.comment = news.getInt("comment");  //评论数
					newsBean.type = news.getInt("type");    //新闻的类型   娱乐   休闲    科技   社会
					newsBean.time = news.getString("time");
					newsBean.des = news.getString("des");
					newsBean.title = news.getString("title");
					newsBean.news_url = news.getString("news_url");
					newsBean.icon_url = news.getString("icon_url");
					
					arrayList.add(newsBean);
				}
				//3.清除旧的数据   将新的数据缓存到数据库中
				NewsDaoUtils newsDaoUtils = NewsDaoUtils.getInstance(context);
				newsDaoUtils.deleteCache();
				newsDaoUtils.saveNews(arrayList);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return arrayList;

	}

	/**
	 * 从上次缓存的数据库中加载数据
	 * @param context
	 * @return
	 */
	public static ArrayList<NewsBean> getNewsForDatabase(Context context){
		return NewsDaoUtils.getInstance(context).getNews();
	}
	
}
