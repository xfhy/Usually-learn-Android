package com.xfhy.javapassdata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	public JNI jni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		jni = new JNI();
		
		Button bt_pass_int = (Button) findViewById(R.id.bt_pass_int);
		Button bt_pass_string = (Button) findViewById(R.id.bt_pass_string);
		Button bt_pass_intarray = (Button) findViewById(R.id.bt_pass_intarray);
		bt_pass_int.setOnClickListener(this);
		bt_pass_string.setOnClickListener(this);
		bt_pass_intarray.setOnClickListener(this);
	}

	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_pass_int:
			Toast.makeText(this, "答案是: "+jni.add(3, 12), Toast.LENGTH_SHORT).show();
			break;
		case R.id.bt_pass_string:
			Toast.makeText(this, "返回的值是"+jni.sayHelloInC("abcdef"), Toast.LENGTH_SHORT).show();
			break;
		case R.id.bt_pass_intarray:
			
			int array[] = {1,2,3,4,5};
			jni.arrElementsIncrease(array);   //jni里面的那个方法是直接修改的内存地址,所以直接调用一下,就已经修改了array的值
			for(int i=0; i<array.length; i++){
				Log.d("xfhy", ""+array[i]);
			}
			
			break;
		default:
			break;
		}
	}

}
