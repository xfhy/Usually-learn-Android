package com.itheima.fileupload;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 2017年1月20日16:59:36
 * 
 * @author XFHY
 * 
 * 文件上传
 * 
 * 
 * 上传路径:http://xfhy-pc:8080/itheima74/servlet/UploaderServlet
 * 上传的文件查看地址 :F:\MyEclipseWork\.metadata\.me_tcat\webapps\itheima74\files
 * 
 */
public class MainActivity extends Activity implements OnClickListener{

    private EditText et_filepath;
	private Button bt_upload;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_upload = (Button)findViewById(R.id.bt_upload);
        et_filepath = (EditText)findViewById(R.id.et_filepath);
        bt_upload.setOnClickListener(this);
    }

    /**
     * 文件上传
     */
    private void fileUpload(){
    	
    	
    	String filePath = et_filepath.getText().toString().trim();   //文件路径
    	String url = "http://192.168.191.1:8080/itheima74/servlet/UploaderServlet";
    	
    	//使用开源Utils做上传操作
    	AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    	
    	//将需要上传的数据封装到 RequestParams对象中
    	RequestParams requestParams = new RequestParams();
    	try {
    		Log.i("xfhy", new File(filePath).exists()+"");
			requestParams.put("filename", new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	asyncHttpClient.post(url, requestParams, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				Log.i("xfhy", "onSuccess");
				if(statusCode == 200){	
					Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
			}
		});
    }
    
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_upload:
			fileUpload();
			break;

		default:
			break;
		}
	}

}
