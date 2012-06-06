package com.mobilelibrary.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.mobilelibrary.entity.BookRecommendEntity;
import com.mobilelibrary.entity.BookSearchEntity;
import com.mobilelibrary.entity.UserEntity;
import com.mobilelibrary.handlerexception.WSError;

/*
 * �����������װJSON���󼰵õ���Ӧ��Դ
 */
public class MobilelibraryResourceFromJSONRequest  {
	
	
	//login
	public UserEntity loginAuthentication(String userID ,String password) throws JSONException, WSError{
		// TODO Auto-generated method stub
		
		//����Ĵ���Ŀǰֻ����������
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("LYC");
		userEntity.setUserId("LYC");
		//����Ĵ���ֻ����������
		
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
			book.setBookText("���ݽṹ");
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
			book.setBookText("���ݽṹ");
			books[i]=book;
			
		}
		//����Ĵ���ֻ����������
		
		
		return books;
	}

}
