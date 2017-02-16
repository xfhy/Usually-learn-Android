package com.itheima.smsbackup.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;

import com.itheima.smsbackup.KXmlSerializer;
import com.itheima.smsbackup.bean.Sms;

/**
 * @author XFHY
 * @date 2017年2月2日 下午5:36:19
 * @package com.itheima.smsbackup.utils
 * @function
 */
public class SmsUtils {

	/**
	 * 备份短信
	 * 
	 * @param context
	 * @param allSms
	 * @return
	 */
	public static void backupSms(Context mContext, final List<Sms> allSms,
			final Handler handler) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				
				FileOutputStream fileOutputStream = null;
				File file = null;
				try {
					// 1.准备文件输出流
					file = new File(Environment.getExternalStorageDirectory().getPath()
							+ "/sms.xml");
					if(!file.exists()){
						file.createNewFile();
					}
					
					fileOutputStream = new FileOutputStream(file);

					// 2.得到XmlSerializer对象
					//Android下,如果短信有emoji表情,Xml.newSerializer()则会备份失败   
					//去百度找了份KXmlSerializer代码,这个可以   不会出错
//					XmlSerializer xmlSerializer = Xml.newSerializer();    
					XmlSerializer xmlSerializer = new KXmlSerializer();
					
					// 2.1设置XmlSerializer的一些参数，比如：设置xml写入到哪个文件中
					// os:xml文件写入流 encoding：流的编码
					xmlSerializer.setOutput(fileOutputStream, "utf-8");

					// 3.序列化一个xml的声明头
					// encoding:xml文件的编码 standalone:是否独立
					xmlSerializer.startDocument("utf-8", true);

					// 4.序列化一个根节点的开始节点
					// namespace:命名空间 name： 标签的名称
					xmlSerializer.startTag(null, "Smss");

					// 5.循环遍历list集合序列化一条条短信

					for (Sms sms : allSms) {
						xmlSerializer.startTag(null, "Sms");

						xmlSerializer.startTag(null, "address");
						// 写一个标签的内容
						xmlSerializer.text(sms.getAddress());
						xmlSerializer.endTag(null, "address");

						xmlSerializer.startTag(null, "date");
						xmlSerializer.text(sms.getDate());
						xmlSerializer.endTag(null, "date");

						xmlSerializer.startTag(null, "read");
						xmlSerializer.text(sms.getRead() + "");
						xmlSerializer.endTag(null, "read");

						xmlSerializer.startTag(null, "body");
						xmlSerializer.text(sms.getBody());
						xmlSerializer.endTag(null, "body");

						if (sms.getPerson() != null) {
							xmlSerializer.startTag(null, "person");
							xmlSerializer.text(sms.getPerson());
							xmlSerializer.endTag(null, "person");
						}

						xmlSerializer.endTag(null, "Sms");
					}

					// 6.序列化一个根节点的结束节点
					xmlSerializer.endTag(null, "Smss");
					// 7.将xml写入到文件中，完成xml的序列化
					xmlSerializer.endDocument();
					
					// 将需要返回的数据封装到handler中
					Message message = new Message();
					message.obj = true;
					handler.sendMessage(message);

				} catch (Exception e) {
					// 将需要返回的数据封装到handler中
					Message message = new Message();
					message.obj = false;
					handler.sendMessage(message);
					e.printStackTrace();
				} finally {
					if (fileOutputStream != null) {
						try {
							fileOutputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				
			}
		}).start();

	}

}
