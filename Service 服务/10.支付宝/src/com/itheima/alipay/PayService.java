package com.itheima.alipay;

import com.itheima.alipay.Iservice.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author  XFHY
 * @date  2017年2月1日 下午1:29:39
 * @package com.itheima.alipay
 * @function 
 */
public class PayService extends Service {

	//将中间人对象返回
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		Log.d("xfhy", "PayService      onCreate");
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		Log.d("xfhy", "PayService      onDestroy");
		super.onDestroy();
	}
	
	//支付方法
	private boolean pay(String name,String pwd,int money){
		Log.d("xfhy", "1判断用户名和密码正否正确");
		Log.d("xfhy", "2验证手机是否携带病毒");
		Log.d("xfhy", "3调用C语言 做一些加密处理 ");
		
		if(name.equals("abc")&&pwd.equals("123")&& money<5000){
			return true;
		} else {
			return false;
		}
	} 
	
	//定义的中间人对象
	private class MyBinder extends Stub{

		@Override
		public boolean callPay(String name, String pwd, int money)
				throws RemoteException {
			return pay(name, pwd, money);
		}
		
	} 
	
}
