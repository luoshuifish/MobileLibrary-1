package com.mobilelibrary.activity;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.BookRecommendAdapter;
import com.mobilelibrary.adapter.BookSearchAdapter;
import com.mobilelibrary.common.LoadingDialog;
import com.mobilelibrary.common.MobilelibraryResourceFromJSONRequest;
import com.mobilelibrary.entity.BookRecommendEntity;
import com.mobilelibrary.entity.BookSearchEntity;
import com.mobilelibrary.handlerexception.WSError;
import com.mobilelibrary.view.FailureBar;
import com.mobilelibrary.view.ProgressBar;
/**
 * search books activity
 * @author Ryan
 *
 */
public class SearchActivity extends BaseActivity{
	
	private ViewFlipper mViewFlipper;
	private Gallery mGallery;
	private ProgressBar mProgressBar;
	private FailureBar mFailureBar;
	private ViewFlipper mSearchFlipper;
	private ListView mSearchListView;
	private Button mSearchButton;
	private EditText mSearchEditText;
	private BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite);
		setTopTitle(this,R.string.book_search);
		
		mGallery = (Gallery)findViewById(R.id.Gallery);
		mProgressBar = (ProgressBar)findViewById(R.id.ProgressBar);
		mFailureBar = (FailureBar)findViewById(R.id.FailureBar);
		mViewFlipper = (ViewFlipper)findViewById(R.id.ViewFlipper);
		
		mSearchEditText = (EditText)findViewById(R.id.et_search_key);
		mSearchButton = (Button)findViewById(R.id.btn_do_search);
		mSearchButton.setOnClickListener(mSearchButtonListener);
		
		new NewsTaskGetBookRecommend().execute((Void)null);
		
		
		mSearchFlipper =(ViewFlipper)findViewById(R.id.SearchViewFlipper);
		
		mSearchListView = (ListView)findViewById(R.id.SearchListView);
		
		
		if(mSearchListView.getCount()==0){
			mSearchFlipper.setDisplayedChild(2);
		}
	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	
	//Gallery
	private class NewsTaskGetBookRecommend extends AsyncTask<Void, WSError, BookRecommendEntity[]> {

		@Override
		public void onPreExecute() {
			mViewFlipper.setDisplayedChild(0);
			mProgressBar.setText(R.string.loading_book_recommend);
			super.onPreExecute();
		}

		@Override
		public BookRecommendEntity[] doInBackground(Void... params) {
			MobilelibraryResourceFromJSONRequest  server = new MobilelibraryResourceFromJSONRequest();
			BookRecommendEntity[] books = null;
			try {
				books = server.getBooksRecommend();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (WSError e){
				publishProgress(e);
			}
			return books;
		}

		@Override
		public void onPostExecute(BookRecommendEntity[] books) {

			if(books != null && books.length > 0){
				mViewFlipper.setDisplayedChild(1);
				BookRecommendAdapter booksAdapter = new BookRecommendAdapter(SearchActivity.this);
				booksAdapter.setList(books);
				mGallery.setAdapter(booksAdapter);
				mGallery.setOnItemClickListener(mGalleryListener);
				mGallery.setSelection(books.length/2, true); 
				
				

			} else {
				mViewFlipper.setDisplayedChild(2);
				mFailureBar.setOnRetryListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						new NewsTaskGetBookRecommend().execute((Void)null);
					}

				});
				mFailureBar.setText(R.string.connection_fail);
			}
			super.onPostExecute(books);
		}

		@Override
		protected void onProgressUpdate(WSError... values) {
			Toast.makeText(SearchActivity.this, values[0].getMessage(), Toast.LENGTH_LONG).show();
			super.onProgressUpdate(values);
		}
		
		

	}
	
	private OnItemClickListener mGalleryListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long id) {
			BookRecommendEntity book = (BookRecommendEntity) adapterView.getItemAtPosition(position);
		}
		
	};
	
	
	private class NewTaskSearchingBook extends LoadingDialog<Void, Integer>{

		private Integer mSearchMode;
		private BaseAdapter mAdapter;

		public NewTaskSearchingBook(Activity activity, int loadingMsg, int failMsg) {
			super(activity, loadingMsg, failMsg);
		}

		@Override
		public Integer doInBackground(Void... params) {
			
			bookSearch();
			return 1;
		}

		@Override
		public void doStuffWithResult(Integer result) {

			mSearchListView.setAdapter(mAdapter);
			
			if(mSearchListView.getCount() > 0){
				mSearchFlipper.setDisplayedChild(0); 
			} else {
				mSearchFlipper.setDisplayedChild(1); 
			}
		    mSearchListView.setOnItemClickListener(mBookClickListener);
		
		}

		private void bookSearch(){
			
			MobilelibraryResourceFromJSONRequest  server = new MobilelibraryResourceFromJSONRequest();
			
			String query = mSearchEditText.getText().toString();
			BookSearchEntity[] books = null;
			try {
				
				books = server.getBooksSearch(query);
				BookSearchAdapter bookAdapter = new BookSearchAdapter(SearchActivity.this); 
				bookAdapter.setList(books);
				bookAdapter.setListView(mSearchListView);
				mAdapter = bookAdapter;

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (WSError e) {
				publishProgress(e);
				this.cancel(true);
			}
		}



		
	}
	
	
	
	private OnClickListener mSearchButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			new NewTaskSearchingBook(SearchActivity.this,
					R.string.wait_msg,
					R.string.wait_msg).execute((Void)null);	
		}

	};
	
	
	private OnItemClickListener mBookClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long time) {
			Intent   book_info_intent = new Intent(SearchActivity.this, BookInfoActivity.class);
			startActivity(book_info_intent);
		}

	};

	
}
