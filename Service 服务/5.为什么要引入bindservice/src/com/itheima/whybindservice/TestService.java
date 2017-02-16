package com.itheima.whybindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author  XFHY
 * @date  2017年1月30日 下午5:27:21
 * @package com.itheima.whybindservice
 * @function 
 */
public class TestService extends Service {

	//当bindService时调用
	@Override
	public IBinder onBind(Intent intent) {
		//3.把定义的中间人对象返回
		return new MyBinder();
	}

	//测试方法
	public void banzheng(int money){
		if(money > 100){
			Toast.makeText(getApplicationContext(), "我是领导,把证给你办了", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "就这点儿钱,还想办事", Toast.LENGTH_SHORT).show();
		}
	}
	
	public class MyBinder extends Binder{
		//2.定义一个方法    调用上面的测试方法
		public void callbanzheng(int money){
			banzheng(money);
		}
	}
	
}
