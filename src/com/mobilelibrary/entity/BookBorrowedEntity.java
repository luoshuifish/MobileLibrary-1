package com.mobilelibrary.entity;

public class BookBorrowedEntity extends BookEntity{
	
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPress() {
		return bookPress;
	}
	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}
	public String getBookPressTime() {
		return bookPressTime;
	}
	public void setBookPressTime(String bookPressTime) {
		this.bookPressTime = bookPressTime;
	}
	//book author
	private String bookAuthor;
	//book press
	private String bookPress;
	//book press time
	private String bookPressTime;

}
