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
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	//book id
	private String bookId;
	//book name
	private String bookText;
	//book image url
	private String bookImageUrl;


}
