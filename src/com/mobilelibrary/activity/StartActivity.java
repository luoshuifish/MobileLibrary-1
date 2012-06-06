package com.mobilelibrary.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobilelibrary.R;
import com.mobilelibrary.common.LoginTask;
import com.mobilelibrary.common.SPConstant;
import com.mobilelibrary.entity.UserEntity;
import com.mobilelibrary.utils.SystemUtils;

/**
 * 
 * @author Ryan
 *
 */
public class StartActivity extends Activity {

	private LinearLayout layout;
	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		sharedPreferences = getSharedPreferences(SPConstant.SP_NAME, MODE_WORLD_READABLE);
		layout = (LinearLayout) this.findViewById(R.id.start_layout);
		
	
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(2000);

		layout.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				boolean isNetWorkAvaliable = SystemUtils.checkNetwork(StartActivity.this);
				if (!isNetWorkAvaliable) {
					AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
					builder.setTitle("网络错误");
					builder.setMessage("网络异常，请检查网络连接");
					builder.setPositiveButton("确认", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					});
					builder.create().show();
				}else {
					boolean isFirstLogin = sharedPreferences.getBoolean(SPConstant.SP_FIRST_LOGIN, true);
					
					String password = sharedPreferences.getString(SPConstant.SP_PASSWORD, "");
					
					if (isFirstLogin || (!isFirstLogin && password.equals(""))) {
						Intent mainIntent = new Intent(StartActivity.this,
								LoginActivity.class);
						StartActivity.this.startActivity(mainIntent);
						StartActivity.this.finish();
					}else {
						String userName = sharedPreferences.getString(SPConstant.SP_USER_ID, "");
						if (!userName.equals("") && !password.equals("")) {
							UserEntity userEntity = new UserEntity();
							userEntity.setUserName(userName);
							userEntity.setPassword(password);
							
							LoginTask loginTask = new LoginTask(StartActivity.this);
							loginTask.execute(userEntity);
						}else {
							Toast.makeText(StartActivity.this, "系统错误", Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
	}
}
