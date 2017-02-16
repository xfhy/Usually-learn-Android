package com.itheima.carmea;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_pic = (Button) findViewById(R.id.bt_pic);
		Button bt_save = (Button) findViewById(R.id.bt_save);

		bt_pic.setOnClickListener(this);
		bt_save.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_pic:
			// create Intent to take a picture and return control to the calling
			// application
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			File file = new File(Environment.getExternalStorageDirectory(),
					"1.png");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

			// start the image capture Intent
			startActivityForResult(intent, 1);
			break;
		case R.id.bt_save:
			// create Intent to take a picture and return control to the calling
			// application
			Intent intent2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

			File file2 = new File(Environment.getExternalStorageDirectory(),
					"1.3gp");
			intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file2));

			// start the image capture Intent
			startActivityForResult(intent2, 2);
			break;
		default:
			break;
		}
	}

	// 当调用的页面关闭的时候被调用
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("xfhy", "哈哈,我被调用了");
		super.onActivityResult(requestCode, resultCode, data);
	}

}
