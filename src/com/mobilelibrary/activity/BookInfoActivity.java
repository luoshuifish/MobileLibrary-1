package com.mobilelibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.PersonInformationAdapter;
import com.mobilelibrary.dao.StoredBookDAO;
import com.mobilelibrary.entity.BookStoredEntity;

public class BookInfoActivity extends BaseActivity{
	
    private ListView personInformationListView;
    private ImageButton btn_store;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_layout);      
        setTopTitle(this, R.string.book_info);
        setBackButton(this, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BookInfoActivity.this.finish();
			}
		});
        
        btn_store = (ImageButton)findViewById(R.id.btn_status_bar_store);
        
        btn_store.setOnClickListener(mStoreClickListener);
   
    }
	
	private OnClickListener mStoreClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			StoredBookDAO storeBookDAO = new StoredBookDAO(BookInfoActivity.this);
			BookStoredEntity testBookBorrowedEntity1 = new BookStoredEntity();
			testBookBorrowedEntity1.setBookId("1");
			testBookBorrowedEntity1.setBookText("android");
			testBookBorrowedEntity1.setBookImageUrl("android");
			testBookBorrowedEntity1.setBookPress("android");
			testBookBorrowedEntity1.setBookPressTime("android");
			storeBookDAO.insert(testBookBorrowedEntity1);
			Toast.makeText(BookInfoActivity.this, R.string.storesuccess, Toast.LENGTH_SHORT).show();
		}
	};

}

