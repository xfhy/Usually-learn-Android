package com.itheima.smsbackup.bean;

/**
 * @author XFHY
 * @date 2017年2月2日 下午4:58:41
 * @package com.itheima.smsbackup.bean
 * @function 短信 模型
 */
public class Sms {

	private String address;
	private String date;
	private int read;
	private String body;
	private String person;

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "发件人:"+person + address + "  日期:" + date + "  是否已读:" + read + "  短信内容:"
				+ body;
	}

}
