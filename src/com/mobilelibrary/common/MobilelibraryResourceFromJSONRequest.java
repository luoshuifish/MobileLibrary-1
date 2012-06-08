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
 * �����������װJSON���󼰵õ���Ӧ��Դ
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
    
	//�˷���������ȡ�Ƽ�������
	public BookRecommendEntity[] getBooksRecommend() throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//����Ĵ���Ŀǰֻ����������
		BookRecommendEntity[] books = new BookRecommendEntity[5];
		
		for (int i = 0; i < 5; i++) {
			
			BookRecommendEntity  book = new BookRecommendEntity();
			book.setBookImageUrl("aa");
			book.setBookText("Android");
			books[i]=book;
			
		}
		//����Ĵ���ֻ����������
		
		
		return books;
	}
	
	//�˷���������ȡ������������
	public BookSearchEntity[] getBooksSearch(String query) throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//����Ĵ���Ŀǰֻ����������
		BookSearchEntity[] books = new BookSearchEntity[5];
		
		for (int i = 0; i < 5; i++) {
			
			BookSearchEntity  book = new BookSearchEntity();
			book.setBookImageUrl("aa");
			book.setBookText("android");
			books[i]=book;
			
		}
		//����Ĵ���ֻ����������
		
		
		return books;
	}

}
