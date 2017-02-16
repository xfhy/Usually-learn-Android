package com.itheima.querycontacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.itheima.querycontacts.bean.Contact;

/**
 * @author  XFHY
 * @date  2017年2月5日 下午5:34:18
 * @package com.itheima.querycontacts
 * @function 联系人操作工具类
 */
public class QueryContactUtils {

	public static List<Contact> getAllContacts(Context context){
		List<Contact> contactLists = new ArrayList<>();
		
		ContentResolver contentResolver = context.getContentResolver();

		// 1.获取uri com.android.contacts是ContactsProvider2的authorities属性
		// raw_contacts是表名
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		// 先查询raw_contacts表的contact_id列,就知道有几条联系人
		Cursor cursor = contentResolver.query(uri,
				new String[] { "contact_id","display_name","sort_key" }, null, null, "sort_key");
		if (cursor != null && cursor.moveToFirst()) {
			do {
				String contact_id = cursor.getString(cursor.getColumnIndex("contact_id"));
				
				//如果用户删除了一条联系人信息,则contact_id为null
				if(contact_id == null){
					continue;
				}
				
				String sortKey = getSortKey(cursor.getString(cursor.getColumnIndex("sort_key")));
				String displayName = cursor.getString(cursor.getColumnIndex("display_name"));
				
				Contact contact = new Contact();
				contact.setId(contact_id);
				contact.setSortKey(sortKey);
				contact.setName(displayName);
				
				//☆☆☆☆   当查询data表时,其实是查询的 data_view表,这里面没有mimetype_id,只有mimetype
				//2.查询联系人信息   mimetype信息类型(姓名,电话等)
				Cursor dataCursor = contentResolver.query(dataUri, new String[]{"data1","mimetype"}, 
						"raw_contact_id=?", new String[]{contact_id}, null);
				if(dataCursor != null  && dataCursor.moveToFirst()){
					do {
						String data1 = dataCursor.getString(dataCursor.getColumnIndex("data1"));
						
						String mimetype = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
						
						if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
							contact.setPhone(data1);   //电话
						} else if(mimetype.equals("vnd.android.cursor.item/email_v2")){
							contact.setEmail(data1);   //邮箱
						}
						
					} while (dataCursor.moveToNext());
					dataCursor.close();
				}
				
				//3.将当前这个联系人添加到列表中
				contactLists.add(contact);
			} while (cursor.moveToNext());
			cursor.close();
		}
		
		//Collections.sort(contactLists);
		return contactLists;
	}

	/**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     * 
     * @param sortKeyString
     * 数据库中读取出的sort key
     * @return 英文字母或者#
     */
	private static String getSortKey(String sortKeyString) {
		String key = sortKeyString.substring(0, 1).toUpperCase(Locale.getDefault());
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
	}
	
}
