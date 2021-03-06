package com.itheima.menutest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 1. 加载菜单
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//想要知道具体点击的是哪个菜单条目,需要重写此方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_add:
			Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	//当点击menu按键的时候会执行这个方法
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		
		//需求:当用户点击menu键时,显示一个对话框
		
		//1. new一个AlertDialog.Builder                            
		AlertDialog.Builder builder = new Builder(this);
		//2. 设置框标题
		builder.setTitle("警告");
		//3. 设置确定按钮
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("xfhy", "确定");
			}
		});
		
		//4. 设置取消按钮
		builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("xfhy", "取消");
			}
		});
		
		//5. 最后一定要记得show
		builder.show();
		
		return false;    //这里本来默认是返回true的
	}
	
	@Override
	public void onBackPressed() {
	}
	
}
