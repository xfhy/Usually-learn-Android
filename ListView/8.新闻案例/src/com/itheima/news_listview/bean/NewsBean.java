package com.itheima.news_listview.bean;


/**
 * 2017年1月19日14:13:43
 * @author XFHY
 *
 * 这是Bean类,模型类
 *
 */
public class NewsBean {
	
	public String title;
	public String des;
	public String news_url;
	public int id;
	public int comment;
	public int type;
	public String time;
	public String icon_url;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return title+des;
	}
	
}
