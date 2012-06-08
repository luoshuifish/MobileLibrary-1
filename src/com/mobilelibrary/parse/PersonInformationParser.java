package com.mobilelibrary.parse;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;
import com.mobilelibrary.entity.UserEntity;
import com.mobilelibrary.handlerexception.WSError;
import com.mobilelibrary.utils.StringUtil;

public class PersonInformationParser extends BaseParser {
	
	@Override
	public Object executeToObject(InputStream in, Context context)
			throws WSError {
		// TODO Auto-generated method stub
		UserEntity userEntity = new UserEntity();
		String json = StringUtil.converStreamToString(in);
		Gson gson =new Gson();
		userEntity = gson.fromJson(json, UserEntity.class);
		System.out.println("userEntity"+userEntity.toString());
		return userEntity;
	}

}
