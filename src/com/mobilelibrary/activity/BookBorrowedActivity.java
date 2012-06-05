package com.mobilelibrary.activity;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.BookBorrowedAdapter;
import com.mobilelibrary.adapter.BookSearchAdapter;
import com.mobilelibrary.common.LoadingDialog;
import com.mobilelibrary.common.MobilelibraryResourceFromJSONRequest;
import com.mobilelibrary.dao.BorrowedBookDAO;
import com.mobilelibrary.entity.BookBorrowedEntity;
import com.mobilelibrary.entity.BookSearchEntity;
import com.mobilelibrary.handlerexception.WSError;

public class BookBorrowedActivity extends BaseActivity{
	
    private ViewFlipper mViewFlipper;
	private ListView mBorrowedListView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrowed_book);
		setTopTitle(this,R.string.my_borrowedbook);
		
		mViewFlipper = (ViewFlipper)findViewById(R.id.ViewFlipper);
		
		mBorrowedListView = (ListView)findViewById(R.id.borrowedListView);
		
		new NewTaskGetBorrowedBookFromDB(BookBorrowedActivity.this,
				R.string.wait_msg,
				R.string.wait_msg).execute((Void)null);	
		//if  borrowed book is null
//		if(mBorrowedListView.getCount()==0){
//			mViewFlipper.setDisplayedChild(0);
//		}


    }
	
	// get borrowed book from db
	private class NewTaskGetBorrowedBookFromDB extends LoadingDialog<Void, Integer>{

		private Integer mSearchMode;
		private BaseAdapter mAdapter;

		public NewTaskGetBorrowedBookFromDB(Activity activity, int loadingMsg, int failMsg) {
			super(activity, loadingMsg, failMsg);
		}

		@Override
		public Integer doInBackground(Void... params) {
			
			//get the  books from borrowed DB
			bookSearch();
			return 1;
		}

		@Override
		public void doStuffWithResult(Integer result) {

			mBorrowedListView.setAdapter(mAdapter);
			
			if(mBorrowedListView.getCount() > 0){
				mViewFlipper.setDisplayedChild(0); 
			} else {
				mViewFlipper.setDisplayedChild(1); 
			}
			mBorrowedListView.setOnItemClickListener(mBookClickListener);
		
		}

		private void bookSearch(){
			
			BorrowedBookDAO  borrowedBookDAO = new BorrowedBookDAO(BookBorrowedActivity.this);
			//下面的代码仅用来测试
			BookBorrowedEntity testBookBorrowedEntity = new BookBorrowedEntity();
			testBookBorrowedEntity.setBookId("1");
			testBookBorrowedEntity.setBookText("数据结构");
			testBookBorrowedEntity.setBookImageUrl("数据结构");
			testBookBorrowedEntity.setBookPress("数据结构");
			testBookBorrowedEntity.setBookPressTime("数据结构");
			BookBorrowedEntity testBookBorrowedEntity1 = new BookBorrowedEntity();
			testBookBorrowedEntity1.setBookId("1");
			testBookBorrowedEntity1.setBookText("数据结构");
			testBookBorrowedEntity1.setBookImageUrl("数据结构");
			testBookBorrowedEntity1.setBookPress("数据结构");
			testBookBorrowedEntity1.setBookPressTime("数据结构");
			borrowedBookDAO.insert(testBookBorrowedEntity);
			borrowedBookDAO.insert(testBookBorrowedEntity1);
			//上面的代码仅用来测试
			ArrayList<BookBorrowedEntity> books = null;
			books = borrowedBookDAO.query(null, null ,null,null);
			BookBorrowedAdapter bookAdapter = new BookBorrowedAdapter(BookBorrowedActivity.this); 
			bookAdapter.setList(books);
			bookAdapter.setListView(mBorrowedListView);
			mAdapter = bookAdapter;

		
		}



		
	}
	
	//borrowed book Listener
	private OnItemClickListener mBookClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long time) {
			//跳传到该书的信息页
		}

	};

}
