package com.mobilelibrary.parse;

import java.io.InputStream;

import com.mobilelibrary.handlerexception.WSError;

import android.content.Context;

public abstract class BaseParser {
	protected final String ENCODE = "UTF-8";
	
	
	public abstract Object executeToObject(InputStream in,Context context)throws WSError;
	

}
