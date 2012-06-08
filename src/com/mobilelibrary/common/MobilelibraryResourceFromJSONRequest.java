package com.mobilelibrary.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobilelibrary.entity.BookRecommendEntity;
import com.mobilelibrary.entity.BookSearchEntity;
import com.mobilelibrary.entity.UserEntity;
import com.mobilelibrary.handlerexception.WSError;
import com.mobilelibrary.net.HttpRequest;
import com.mobilelibrary.parse.PersonInformationParser;

/*
 * 这个类用来封装JSON请求及得到响应资源
 */
public class MobilelibraryResourceFromJSONRequest  {
	
	
	//login
	public UserEntity loginAuthentication(String userID ,String password) throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//generate JSON object
		JSONObject param = new JSONObject();
		param.put("userId", userID);
		param.put("password", password);

		InputStream   inputStream  = HttpRequest.requestByXML("url",param.toString());
		
		//		UserEntity  userEntity = (UserEntity)new PersonInformationParser().executeToObject(inputStream, null);

		//test
		UserEntity  userEntity = (UserEntity)new PersonInformationParser().executeToObject(new ByteArrayInputStream(param.toString().getBytes()), null);
		
		return userEntity;
	}
    
	//此方法用来获取推荐的新书
	public BookRecommendEntity[] getBooksRecommend() throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//下面的代码目前只是用来测试
		BookRecommendEntity[] books = new BookRecommendEntity[5];
		
		for (int i = 0; i < 5; i++) {
			
			BookRecommendEntity  book = new BookRecommendEntity();
			book.setBookImageUrl("aa");
			book.setBookText("Android");
			books[i]=book;
			
		}
		//上面的代码只是用来测试
		
		
		return books;
	}
	
	//此方法用来获取搜索到的新书
	public BookSearchEntity[] getBooksSearch(String query) throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//下面的代码目前只是用来测试
		BookSearchEntity[] books = new BookSearchEntity[5];
		
		for (int i = 0; i < 5; i++) {
			
			BookSearchEntity  book = new BookSearchEntity();
			book.setBookImageUrl("aa");
			book.setBookText("android");
			books[i]=book;
			
		}
		//上面的代码只是用来测试
		
		
		return books;
	}

}
