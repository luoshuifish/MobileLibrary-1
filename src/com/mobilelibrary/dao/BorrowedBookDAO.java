package com.mobilelibrary.dao;

import java.util.ArrayList;

import com.mobilelibrary.entity.BookBorrowedEntity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/*
 * borrowed book DB
 */
public class BorrowedBookDAO extends BaseDAO {
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookImageUrl() {
		return bookImageUrl;
	}
	public void setBookImageUrl(String bookImageUrl) {
		this.bookImageUrl = bookImageUrl;
	}
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
	private String bookId = "book_id";
	private String bookName = "book_name";
	private String bookImageUrl = "book_image_url";
	private String bookAuthor = "book_author";
	private String bookPress = "book_press";
	private String bookPressTime = "book_press_time";

	private Context context;
	
	public BorrowedBookDAO(Context context){
		this.context = context;

	}
	@Override
	public String createTableString() {
		// TODO Auto-generated method stub
		
		StringBuffer sql_borrowed_books = new StringBuffer();
		sql_borrowed_books.append("create table " + TABLE_BORROWED_BOOK + "(_id integer primary key autoincrement,")
		.append("book_id text,")
		.append("book_name text,")
		.append("book_image_url text,")
		.append("book_author text,")
		.append("book_press text,")
		.append("book_press_time text);");
		return sql_borrowed_books.toString();
	}

	@Override
	public String dropTable() {
		// TODO Auto-generated method stub
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.DROP_TABLE);
		buffer.append(TABLE_BORROWED_BOOK);
		
		return buffer.toString();

	}

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		BookBorrowedEntity entity = (BookBorrowedEntity)obj;
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();

		values.put(bookId, entity.getBookId());
		values.put(bookName, entity.getBookText());
		values.put(bookImageUrl, entity.getBookImageUrl());
		values.put(bookAuthor, entity.getBookAuthor());
		values.put(bookPress, entity.getBookPress());
		values.put(bookPressTime, entity.getBookPressTime());

		resolver.insert(URI_BORROWED_BOOK, values);
		
	}

	@Override
	public void delete(String where, String[] selectionArgs) {
		// TODO Auto-generated method stub
		ContentResolver resolver = context.getContentResolver();
		resolver.delete(URI_BORROWED_BOOK, where, selectionArgs);
		
	}

	@Override
	public ArrayList<BookBorrowedEntity> query(String[] selections, String where,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor =  resolver.query(URI_BORROWED_BOOK, selections, where, selectionArgs, null);
		ArrayList<BookBorrowedEntity> arrayList = new ArrayList<BookBorrowedEntity>();
		
		BookBorrowedEntity entity = null;
		
		if(null==cursor)
			return arrayList;
		if(cursor.getCount()<1){
			cursor.close();
			return arrayList;
		}
		
		int count = cursor.getCount();
		
		if(count>0)
			cursor.moveToFirst();
		
		do {

			entity = new BookBorrowedEntity();

			
			entity.setBookId(cursor.getString(cursor.getColumnIndex(bookId)));
			entity.setBookText(cursor.getString(cursor.getColumnIndex(bookName)));
			entity.setBookImageUrl(cursor.getString(cursor.getColumnIndex(bookImageUrl)));
			entity.setBookAuthor(cursor.getString(cursor.getColumnIndex(bookAuthor)));
			entity.setBookPress(cursor.getString(cursor.getColumnIndex(bookPress)));
			entity.setBookPressTime(cursor.getString(cursor.getColumnIndex(bookPressTime)));

						
			arrayList.add(entity);
			
		} while (cursor.moveToNext());
		
		cursor.close();
		
		return arrayList;

	}

	@Override
	public void update(Object obj, String where, String[] selectionArgs) {
		// TODO Auto-generated method stub
		BookBorrowedEntity entity  = (BookBorrowedEntity )obj;
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		
		values.put(bookId, entity.getBookId());
		values.put(bookName, entity.getBookText());
		values.put(bookImageUrl, entity.getBookImageUrl());
		values.put(bookAuthor, entity.getBookAuthor());
		values.put(bookPress, entity.getBookPress());
		values.put(bookPressTime, entity.getBookPressTime());
		
		resolver.update(URI_BORROWED_BOOK, values, where, selectionArgs);
		
	}
	
}
