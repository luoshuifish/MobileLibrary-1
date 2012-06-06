package com.mobilelibrary.entity;

import java.io.Serializable;


public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;


	//user ID
	private String userId;
	//user Name
	private String userName;
	//user Name
	private String password;					
		
	public UserEntity(){}
	
	public UserEntity(String userId, String realName) {
		super();
		this.userId = userId;
		this.userName = realName;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
