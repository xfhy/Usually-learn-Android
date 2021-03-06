package com.xfhy.viewpager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements OnPageChangeListener {

	private ViewPager viewPager;
	private List<ImageView> imageViewList;
	private LinearLayout ll_point;
	private String contentDescs[];
	private TextView tv_info;
	private int lastPointPosition;
	private boolean isRunning = true;

	private final static int UPDATE_VIEWPAGER_POS = 1000; // 更新ViewPager的位置

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE_VIEWPAGER_POS:
				viewPager.setCurrentItem(msg.arg1 + 1); // 设置当前的ViewPager的位置
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews(); // 初始化适配器

		initData(); // 初始化数据

		initAdapter(); // 初始化视图

		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				//要做的事情
				viewPager.setCurrentItem(viewPager.getCurrentItem() + 1); // 设置当前的ViewPager的位置
				handler.postDelayed(this, 2000);
			}
		};
		handler.postDelayed(runnable, 2000);   //2.启动计时器
		
		/*new Thread(new Runnable() {

			@Override
			public void run() {

				while (isRunning) {
					SystemClock.sleep(2000); // 睡2秒

					Log.d("xfhy", "当前位置:" + viewPager.getCurrentItem());

					Message msg = Message.obtain();
					msg.what = UPDATE_VIEWPAGER_POS;
					msg.arg1 = viewPager.getCurrentItem(); // 将当前的ViewPager位置封装到msg的arg1中
					handler.sendMessage(msg);   //发送msg
				}
			}
		}).start();*/

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isRunning = false;
	}
	
	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		// 设置第一个小白点默认是选中的
		ll_point.getChildAt(0).setEnabled(true);
		tv_info.setText(contentDescs[0]);

		viewPager.setAdapter(new MyAdapter());

		// 一开始就跳到一个很大的数,这样就可以无限往左滑了
		// 保证那个数必须是0号位置 当viewpager需要跳转到数很大的那一项时,会出现小bug,点击时乱跳转
		// 所以设置的数小一点 500万就行了
		// int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 %
		// imageViewList.size());
		viewPager.setCurrentItem(5000000);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		imageViewList = new ArrayList<>();

		int imageResIds[] = { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e }; // 资源数组
		contentDescs = new String[] { "巩俐不低俗，我就不能低俗", "扑树又回来啦！再唱经典老歌引万人大合唱",
				"揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" };

		ImageView imageView;
		int length = imageResIds.length;
		View pointView;
		LayoutParams layoutParams;
		for (int i = 0; i < length; i++) {
			imageView = new ImageView(this);
			imageView.setBackgroundResource(imageResIds[i]);
			imageViewList.add(imageView); // 添加到列表中

			// 小白点
			pointView = new View(this);
			pointView.setBackgroundResource(R.drawable.selector_bg_point); // 设置背景
			layoutParams = new LinearLayout.LayoutParams(15, 15); // 设置长宽为10dp

			// 如果不是第一个,则增加间距
			if (i != 0) {
				layoutParams.leftMargin = 20;
			}

			pointView.setEnabled(false); // 默认设置小白点是不可用,即背景是灰色的(上面有个selector_bg_point会根据是否可用来选择背景)
			ll_point.addView(pointView, layoutParams); // 添加小白点到下面的LinearLayout中

		}

	}

	/**
	 * 初始化视图
	 */
	private void initViews() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		ll_point = (LinearLayout) findViewById(R.id.ll_point);
		tv_info = (TextView) findViewById(R.id.tv_info);

		viewPager.setOnPageChangeListener(this);
	}

	/**
	 * 重写PagerAdapter
	 * 必须实现里面的4个方法getCount(),isViewFromObject(),instantiateItem(),destroyItem()
	 */
	class MyAdapter extends PagerAdapter {

		// 返回资源一共的条目
		@Override
		public int getCount() {
			return Integer.MAX_VALUE; // 返回一个很大的值,这样就能无限地往右边滑了
		}

		// 3. 复用判断逻辑
		// 其实在加载时,默认是加载左右两边的图(比如0,1,2 1是现在正在浏览的,而当浏览到2时,0被销毁),如果用户回到刚刚浏览过的,则复用
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		// 1. 返回条目需要显示的内容 必须重写,否则报异常
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Log.d("xfhy", "instantiateItem 加载" + position);

			int newPostion = position % imageViewList.size();

			ImageView imageView = imageViewList.get(newPostion);
			container.addView(imageView); // 将需要显示的图片加载到container中
			return imageView;
		}

		// 2. 需要在这里面移除 比如0,1,2 1是现在正在浏览的,而当浏览到2时,0被销毁 0需要被移除
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			Log.d("xfhy", "destroyItem 销毁" + position);
			// object 要销毁的对象
			container.removeView((View) object);
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// 滚动状态变化时调用
	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {
		// 滚动时调用
	}

	@Override
	public void onPageSelected(int position) {
		// 新的条目被选中时调用

		int newPostion = position % imageViewList.size();

		tv_info.setText(contentDescs[newPostion]);

		// 将上次滑过的小白点置为false
		ll_point.getChildAt(lastPointPosition).setEnabled(false);
		ll_point.getChildAt(newPostion).setEnabled(true);

		lastPointPosition = newPostion;

	}

}
