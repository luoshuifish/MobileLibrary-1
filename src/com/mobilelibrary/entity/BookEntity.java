package com.mobilelibrary.entity;

public class BookEntity {
	
	public String getBookText() {
		return bookText;
	}
	public void setBookText(String bookText) {
		this.bookText = bookText;
	}
	public String getBookImageUrl() {
		return bookImageUrl;
	}
	public void setBookImageUrl(String bookImageUrl) {
		this.bookImageUrl = bookImageUrl;
	}
	//ͼ��ı���
	private String bookText;
	//ͼ��ͼƬ��url��ַ
	private String bookImageUrl;


}
