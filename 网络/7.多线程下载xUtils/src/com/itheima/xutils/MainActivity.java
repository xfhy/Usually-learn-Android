package com.itheima.xutils;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.bt_download).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		EditText et_url = (EditText)findViewById(R.id.et_url);
		String url = et_url.getText().toString().trim();
		
		//1.创建HttpUtils对象
		HttpUtils httpUtils = new HttpUtils();
		
		String target = Environment.getExternalStorageDirectory()+"/xfhy/feiq.exe";
		//2.调用download方法url:下载的地址  target：下载的目录   callback：回调 
		httpUtils.download(url, target, new RequestCallBack<File>() {
			
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				System.out.println("total:"+total+"    current:"+current);
				super.onLoading(total, current, isUploading);
			}
			
			//下载成功
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				System.out.println(responseInfo.result);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				
			}
		});
	}

}
