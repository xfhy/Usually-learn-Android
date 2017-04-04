package com.xfhy.dialogfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xfhy.dialogfragment.LoginDialogFragment.LoginInputListener;

/**
 * 2017年4月3日10:43:04
 * 
 * @author xfhy
 * 
 *         概述:DialogFragment在android
 *         3.0时被引入。是一种特殊的Fragment，用于在Activity的内容之上展示一个模态的对话框。
 *         典型的用于：展示警告框，输入框，确认框等等
 *         。在DialogFragment产生之前，我们创建对话框：一般采用AlertDialog和Dialog。
 *         注：官方不推荐直接使用Dialog创建对话框。
 * 
 *         好处与用法:1.使用DialogFragment来管理对话框，当旋转屏幕和按下后退键时可以更好的管理其声明周期，
 *         它和Fragment有着基本一致的声明周期。
 *         且DialogFragment也允许开发者把Dialog作为内嵌的组件进行重用，类似Fragment
 *         （可以在大屏幕和小屏幕显示出不同的效果）。
 *         2.当屏幕旋转时,Activity的周期会变化,如果对话框里面有输入的数据,传统的Dialog会在log报错,并且数据会丢失.但是
 *         dialogfragment不但没有报错,而且用户输入的数据没有丢失.
 * 
 *         使用DialogFragment至少需要实现onCreateView或者onCreateDIalog方法。
 *         onCreateView即使用定义的xml布局文件展示Dialog
 *         。onCreateDialog即利用AlertDialog或者Dialog创建出Dialog。
 * 
 */

public class MainActivity extends Activity implements OnClickListener,LoginInputListener {

	private Button bt_edit_name;
	private Button bt_login;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_edit_name = (Button) findViewById(R.id.bt_edit_name);
		bt_login = (Button) findViewById(R.id.bt_login);
		
		bt_edit_name.setOnClickListener(this);
		bt_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_edit_name:
			showEditNameDialog();
			break;
		case R.id.bt_login:
			showLoginDialog();
			break;
		default:
			break;
		}
	}

	/**
	 * 显示编辑名称对话框
	 */
	private void showEditNameDialog() {
		EditNameDialogFragment editNameDialogFragment = new EditNameDialogFragment();
		editNameDialogFragment.show(getFragmentManager(), "EditNameDialog");
	}

	/**
	 * 显示登录对话框
	 */
	private void showLoginDialog() {
		LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
		loginDialogFragment.show(getFragmentManager(), "LoginDialog");
	}

	/**
	 * 登录对话框的监听器
	 */
	@Override
	public void onLoginInputComplete(String username, String password) {
		Toast.makeText(this, "username : "+username+"    password : "+password, Toast.LENGTH_SHORT).show();
	}
	
}
