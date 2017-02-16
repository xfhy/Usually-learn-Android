package com.itheima.remoteservice;

import com.itheima.remoteservice.Iservice.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author  XFHY
 * @date  2017年2月1日 上午10:52:56
 * @package com.itheima.remoteservice
 * @function 
 */
public class RemoteService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	private void testMethod(){
		Log.d("xfhy", "我是远程服务里面的方法");
	}
	
	//中间人对象     可提供给外部访问这个服务里面的方法
	private class MyBinder extends Stub{

		@Override
		public void callTestMethod() throws RemoteException {
			testMethod();
		}
		
	}
	
}
