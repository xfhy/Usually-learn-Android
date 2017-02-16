package com.itheima.autocompletetextview;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.os.Build;

public class MainActivity extends Activity {

	// 1.准备数据
	private static final String[] COUNTRIES = new String[] { "Belgium",
			"France", "Italy", "Germany", "Spain" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AutoCompleteTextView actv_test = (AutoCompleteTextView) findViewById(R.id.actv_test);

		//2. 创建adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		
		//3. 设置adapter
		actv_test.setAdapter(adapter);

	}

}
