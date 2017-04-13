package com.itheima.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_plain;
	private Button bt_sinsec;
	private Button bt_multsec;
	private Button bt_progress;

	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		
		bt_plain = (Button) findViewById(R.id.bt_plain);
		bt_sinsec = (Button) findViewById(R.id.bt_sinsec);
		bt_multsec = (Button) findViewById(R.id.bt_multsec);
		bt_progress = (Button) findViewById(R.id.bt_progress);

		bt_plain.setOnClickListener(this);
		bt_sinsec.setOnClickListener(this);
		bt_multsec.setOnClickListener(this);
		bt_progress.setOnClickListener(this);
		
	}

	//普通对话框
	private void onClick1() {
		AlertDialog.Builder dialog = new Builder(this);
		dialog.setTitle("警告");
		dialog.setMessage("手机即将关机");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("xfhy", "确定");
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d("xfhy", "取消");
			}
		});
		
		//最后一定要记得show
		dialog.show();
	}

	//单选
	private void onClick2() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择你喜欢的课程");
		final String items[] = {"Android","IOS","PHP"};
		builder.setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//1.取出数据
				String item = items[which];
				Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
				
				//2.关闭对话框
				dialog.dismiss();
			}
		});
		
		//最后一定要记得show
		builder.show();
	}

	//多选
	private void onClick3() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("请选择喜欢的水果");
		final String[] fruits = {"荔枝","苹果","橘子","甘蔗","榴莲","香蕉","梨子"};
		final boolean[] checkedItems = {true,false,false,false,false,false,true};
		
		//多选
		builder.setMultiChoiceItems(fruits, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			}
		});
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				StringBuffer stringBuffer = new StringBuffer();
				//将选中的数据取出来
				for (int i=0; i<checkedItems.length; i++) {
					//判断是否选中
					if(checkedItems[i]){
						stringBuffer.append(fruits[i]+" ");
					}
				}
				Toast.makeText(mContext, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		builder.show();
	}
	
	//进度条对话框

	private void onClick4() {
		//1.创建一个ProgressDialog对象      与Progress相关的可以在子线程更新UI
		final ProgressDialog progressDialog = new ProgressDialog(mContext);
		//2.设置标题
		progressDialog.setTitle("玩命儿加载ing");
		
		//3.设置风格,默认的风格是转圈儿     现在设置成水平
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		new Thread(){
			public void run() {
				for (int i = 0; i <= 100; i++) {
					SystemClock.sleep(50);   //睡眠50毫秒
					progressDialog.setProgress(i);    //设置进度条的进度
				}
				
				//关闭对话框
				progressDialog.dismiss();
			};
		}.start();    //开启线程
		//4.最后记得show
		progressDialog.show();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bt_plain:
			onClick1();
			break;
		case R.id.bt_sinsec:
			onClick2();
			break;
		case R.id.bt_multsec:
			onClick3();
			break;
		case R.id.bt_progress:
			onClick4();
			break;
		default:
			break;
		}
	}

}
