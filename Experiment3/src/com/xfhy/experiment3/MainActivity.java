package com.xfhy.experiment3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv_second_info;
	private final static int SECOND_ACTIVITY_CODE = 10001;
	private Button bt_open_second_forresult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_open_second_forresult = (Button) findViewById(R.id.bt_open_second_forresult);
		tv_second_info = (TextView) findViewById(R.id.tv_second_info);

		bt_open_second_forresult.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_open_second_forresult:
			Intent intent = new Intent(this, SecondActivity.class);
			intent.putExtra(SecondActivity.NAME_KEY, "张三");
			intent.putExtra(SecondActivity.AGE_KEY, 18);
			startActivityForResult(intent, SECOND_ACTIVITY_CODE);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 如果是用户按下的确定之后才返回到这里 而不是按返回键到这里
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case SECOND_ACTIVITY_CODE:
				tv_second_info.setText(data.getStringExtra("info"));
				break;
			default:
				break;
			}
		}
	}

}
