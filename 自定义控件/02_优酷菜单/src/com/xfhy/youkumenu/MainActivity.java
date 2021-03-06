package com.xfhy.youkumenu;

import com.xfhy.youkumenu.utils.AnimationUtils;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract.CommonDataKinds.Event;

public class MainActivity extends Activity implements OnClickListener{

	private RelativeLayout rl_level1;
	private RelativeLayout rl_level2;
	private RelativeLayout rl_level3;
	private boolean isLevel1Display = true;
	private boolean isLevel2Display = true;
	private boolean isLevel3Display = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		
	}

	private void initView() {
		findViewById(R.id.ib_home).setOnClickListener(this); 
		findViewById(R.id.ib_menu).setOnClickListener(this); 
		rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			
			if(AnimationUtils.runningAnimationCount > 0) {  //记录当前有多少个正在执行的动画    如果大于0则不需要执行动画
				return true;
			}
			
			long delay = 0;
			if(isLevel1Display) {   //如果一级菜单显示   则将其他的都转出
				
				if(isLevel3Display) {
					AnimationUtils.rotateOutAnim(rl_level3, 0);   //转出三级菜单
					isLevel3Display = false;
					delay += 200;
				}
				
				if(isLevel2Display) {
					AnimationUtils.rotateOutAnim(rl_level2, delay);   //转出二级菜单
					isLevel2Display =  false;
					delay += 200;
				}
				
				AnimationUtils.rotateOutAnim(rl_level1, delay);        //转出一级菜单
				isLevel1Display = !isLevel1Display;
			} else {
				delay = 0; 
				AnimationUtils.rotateInAnim(rl_level1,delay);        //转入一级菜单
				isLevel1Display = !isLevel1Display;
				delay += 200;
				AnimationUtils.rotateInAnim(rl_level2,delay);        //转入二级菜单
				isLevel2Display = !isLevel2Display;
				delay += 200;
				AnimationUtils.rotateInAnim(rl_level3,delay);        //转入三级菜单
				isLevel3Display = !isLevel3Display;
			}
			
			break;
		default:
			break;
		}
		return false;
	}
	
	@Override
	public void onClick(View v) {
		
		if(AnimationUtils.runningAnimationCount > 0) {  //记录当前有多少个正在执行的动画    如果大于0则不需要执行动画
			return ;
		}
		
		switch (v.getId()) {
		case R.id.ib_home:
			long delay = 0;
			if(isLevel2Display) {
				
				if(isLevel3Display) {
					AnimationUtils.rotateOutAnim(rl_level3, delay);
					isLevel3Display = false;
					delay = 200;
				}
				
				AnimationUtils.rotateOutAnim(rl_level2,delay);   //播放中间层RelativeLayout的动画   转出
			} else {
				AnimationUtils.rotateInAnim(rl_level2,delay);   //播放中间层RelativeLayout的动画   转入
			}
			isLevel2Display = !isLevel2Display;
			break;
		case R.id.ib_menu:
			if(isLevel3Display) {
				AnimationUtils.rotateOutAnim(rl_level3,0);   //播放最外层RelativeLayout的动画   转出
			} else {
				AnimationUtils.rotateInAnim(rl_level3,0);   //播放最外层RelativeLayout的动画   转入
			}
			isLevel3Display = !isLevel3Display; 
			break;
		default:
			break;
		}
	}

}
