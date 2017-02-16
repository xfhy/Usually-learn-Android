package com.itheima.tiger;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Context mContext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        
        ListView listView1 = (ListView)findViewById(R.id.lv_tiger1);
        ListView listView2 = (ListView)findViewById(R.id.lv_tiger2);
        ListView listView3 = (ListView)findViewById(R.id.lv_tiger3);
        
        TigerAdapter tigerAdapter = new TigerAdapter();
        listView1.setAdapter(tigerAdapter);
        listView2.setAdapter(tigerAdapter);
        listView3.setAdapter(tigerAdapter);
        
    }

    class TigerAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 500;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			TextView textView = null;
			
			//重用convertView
			if(convertView != null){
				textView = (TextView)convertView;
			} else {
				textView = new TextView(mContext);
			}
			
			Random random = new Random();
			int number = random.nextInt(100);
			if(number <20){
				textView.setTextColor(Color.parseColor("#ff00ff"));//设置textview文字颜色
				textView.setText("桃");
			}else if(number < 40){
				textView.setTextColor(Color.YELLOW);//设置textview文字颜色
				textView.setText("杏");
			}else if(number <60){
				textView.setTextColor(Color.GREEN);//设置textview文字颜色
				textView.setText("梨");
			}else if(number <80){
				textView.setTextColor(Color.RED);//设置textview文字颜色
				textView.setText("枣");
			}else {
				textView.setTextColor(Color.parseColor("#666666"));//设置textview文字颜色
				textView.setText("瓜");
			}
			
			textView.setTextSize(58);
			
			return textView;
		}
    	
    }
    
}
