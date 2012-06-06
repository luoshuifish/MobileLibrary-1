package com.mobilelibrary.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.mobilelibrary.R;
import com.mobilelibrary.activity.LoginActivity;
import com.mobilelibrary.entity.UserEntity;
import com.mobilelibrary.handlerexception.WSError;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;


/**
 * @author Ryan
 *
 */
public class LoginTask extends AsyncTask<UserEntity, Integer, UserEntity> {
	private String userName;
	private String password;
	
	private Activity context;
	private ProgressDialog progressDialog;
	private SharedPreferences sharedPreferences;
	private boolean isFirstLogin;
	
	private String message;

	public LoginTask(Activity context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences(SPConstant.SP_NAME, Context.MODE_WORLD_WRITEABLE);
		isFirstLogin = sharedPreferences.getBoolean(SPConstant.SP_FIRST_LOGIN, true);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (isFirstLogin) {
			message = "第一次登陆请等待..";
		}else {
			message = "请等待...";
		}
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(message);
		progressDialog.show();
	}

	@Override
	protected UserEntity doInBackground(UserEntity... params) {
		UserEntity userEntity = null;
		userName = params[0].getUserId();
		password = params[0].getPassword();
		
		MobilelibraryResourceFromJSONRequest server = new MobilelibraryResourceFromJSONRequest();
		
		try {
			userEntity = server.loginAuthentication(userName,password);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WSError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userEntity;
	}

	@Override
	protected void onPostExecute(UserEntity result) {
		super.onPostExecute(result);
		
		if (result == null) {
			progressDialog.dismiss();
			Toast.makeText(context, "登陆失败", Toast.LENGTH_LONG).show();
		}else {
				
				Editor editor = sharedPreferences.edit();
				editor.putBoolean(SPConstant.SP_FIRST_LOGIN, false);
				editor.putString(SPConstant.SP_PASSWORD, result.getPassword());
				editor.putString(SPConstant.SP_USER_ID, result.getUserId());

				editor.commit();
				
				progressDialog.dismiss();
				
				Toast.makeText(context, context.getString(R.string.login_success), Toast.LENGTH_SHORT).show();
				
				Intent loginIntent = new Intent(context,com.mobilelibrary.activity.MainTabActivity.class);
				loginIntent.putExtra("loginInfo", result);
				context.startActivity(loginIntent);
				context.finish();
			}
	}
}
