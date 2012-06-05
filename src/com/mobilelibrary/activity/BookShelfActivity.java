package com.mobilelibrary.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilelibrary.R;

public class BookShelfActivity extends BaseActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookshelf);
        
//        setTopTitle(this, R.string.my_book_shelves);
//        setBackButton(this, new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				BookShelfActivity.this.finish();
//			}
//		});
    }
}