package com.mobilelibrary.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilelibrary.R;
import com.mobilelibrary.common.LoginTask;
import com.mobilelibrary.common.SPConstant;
import com.mobilelibrary.entity.UserEntity;


public class LoginActivity extends BaseActivity  {
	private final static int CHANGE_ACCOUNT = 1;
	
	private EditText userNameEditText;
	private EditText passwordeEditText;
	private String userId;
	private String password;
	private Button loginButton;
	private Button exchangeAccountButton;
	private TextView userNameTextView;
	private SharedPreferences sp;
	private boolean isFirstLogin;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.login);
		
		userNameEditText = (EditText) this.findViewById(R.id.et_account);
		passwordeEditText = (EditText) this.findViewById(R.id.et_password);
		loginButton = (Button) this.findViewById(R.id.btn_login);
		exchangeAccountButton = (Button) this.findViewById(R.id.btn_exchange_account);
		userNameTextView = (TextView) this.findViewById(R.id.tv_username);
		sp = getSharedPreferences(SPConstant.SP_NAME, MODE_WORLD_WRITEABLE);
		
		isFirstLogin = sp.getBoolean(SPConstant.SP_FIRST_LOGIN, true);
		
		String password = sp.getString(SPConstant.SP_PASSWORD, "");
		
		if (isFirstLogin) {
			exchangeAccountButton.setVisibility(View.INVISIBLE);
			userNameTextView.setVisibility(View.GONE);
			userNameEditText.setVisibility(View.VISIBLE);
		}
		
		if (!isFirstLogin && password.equals("")) {
			exchangeAccountButton.setVisibility(View.VISIBLE);
			userNameEditText.setVisibility(View.GONE);
			userNameTextView.setVisibility(View.VISIBLE);
			
			String userName = sp.getString(SPConstant.SP_USER_ID, "");
			
			if (userName.equals("")) {
				userNameTextView.setText("无法获取");
			}else{
				userNameTextView.setText(userName);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (sp.getBoolean(SPConstant.SP_FIRST_LOGIN, true)) {
					userId = userNameEditText.getText().toString();
				}else {
					userId = userNameTextView.getText().toString();
				}
				password = passwordeEditText.getText().toString();
				
				if (userId.equals("") || password.equals("")) {
					
					Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.user_pwd_not_empty), Toast.LENGTH_SHORT).show();

				}else {
					UserEntity userEntity = new UserEntity();
					userEntity.setUserId(userId);
					userEntity.setPassword(password);
					LoginTask loginTask = new LoginTask(LoginActivity.this);
					loginTask.execute(userEntity);
				}
			}
		});
		
		
	}
	
	private Handler myHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == CHANGE_ACCOUNT) {
				
				userNameTextView.setVisibility(View.GONE);
				userNameEditText.setVisibility(View.VISIBLE);
				userNameEditText.setText("");
				passwordeEditText.setText("");
				
				exchangeAccountButton.setVisibility(View.GONE);
				
				getApplicationContext().stopService(new Intent("com.mobileclient.service.postlocation"));
				
				getApplicationContext().stopService(new Intent("com.mobileclient.service.heartbeat"));
				
				progressDialog.dismiss();
				
				Toast.makeText(LoginActivity.this, "切换成功", Toast.LENGTH_SHORT).show();
			}
		}
		
	};
	

}
