package com.mobilelibrary.provider;

import com.mobilelibrary.dao.BaseDAO;
import com.mobilelibrary.dao.BorrowedBookDAO;
import com.mobilelibrary.dao.StoredBookDAO;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
/**
 * database provider
 * @author Ryan
 *
 */
public class LibraryProvider extends ContentProvider {

	private static final String TAG   = "LibraryProvider";
	private final String DB_NAME      = "mobilelibrary";
	private final int DB_VERSION      = 1;
	
	private SQLiteDatabase db;
	private static UriMatcher mUriMatcher;
	
	
	
	private static final  int BORROWED_BOOK_CODE = 1;
	private static final  int STORED_BOOK_CODE = 2;

	/**
	 * 
	 * set URI id in for created table
	 * */
	static{
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(BaseDAO.AUTHORITIES, BaseDAO.TABLE_BORROWED_BOOK, BORROWED_BOOK_CODE);
		mUriMatcher.addURI(BaseDAO.AUTHORITIES, BaseDAO.TABLE_STORED_BOOK, STORED_BOOK_CODE);
	}
	
	
	
	private class DBHelper extends SQLiteOpenHelper{
		
		public DBHelper(Context context) {
			/* create database*/
			super(context, DB_NAME, null, DB_VERSION);
		}

		/**
		 * create table
		 * */
		@Override
		public void onCreate(SQLiteDatabase db) {
			BorrowedBookDAO borrowedBookDAO = new BorrowedBookDAO(getContext());
			StoredBookDAO storedBookDAO = new StoredBookDAO(getContext());


			/* create the new table*/
			db.execSQL(borrowedBookDAO.createTableString());
			db.execSQL(storedBookDAO.createTableString());

		
		}

		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onCreate(db);
		}
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int matchCode = mUriMatcher.match(uri);

		switch (matchCode) {
		
		case BORROWED_BOOK_CODE:
			
			db.delete(BaseDAO.TABLE_BORROWED_BOOK, selection, selectionArgs);
			break;
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return uri.toString();

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		int matchCode = mUriMatcher.match(uri);
		
		switch (matchCode) {
		
		case BORROWED_BOOK_CODE:
			
			db.insert(BaseDAO.TABLE_BORROWED_BOOK, null, values);
			
			break;
			
		case STORED_BOOK_CODE:
			
			db.insert(BaseDAO.TABLE_STORED_BOOK, null, values);
			
			break;
		}
		
		return null;
	
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		db = new DBHelper(getContext()).getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		int matchCode = mUriMatcher.match(uri);
		Cursor cursor = null;
		
		switch (matchCode) {
		
		
		case BORROWED_BOOK_CODE:
			
			cursor = db.query(BaseDAO.TABLE_BORROWED_BOOK, null, selection, selectionArgs, null, null, null);
			
			break;
		case STORED_BOOK_CODE:
			
			cursor = db.query(BaseDAO.TABLE_STORED_BOOK, null, selection, selectionArgs, null, null, null);
			
			break;
		
		}
		
		return cursor;
	
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int matchCode = mUriMatcher.match(uri);
		
		switch (matchCode) {
		
		case BORROWED_BOOK_CODE:

			db.update(BaseDAO.TABLE_BORROWED_BOOK, values, selection, selectionArgs);
			
			break;
		}
		
		return 0;
	}

}
