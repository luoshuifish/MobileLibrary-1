package com.mobilelibrary.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.BooksAdapter;
import com.mobilelibrary.view.ShelvesView;

public class BookShelfActivity extends BaseActivity {
	
	private ShelvesView gridView;
	private BooksAdapter gridAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookshelf);
        
        setTopTitle(this, R.string.my_book_shelves);
        setBackButton(this, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BookShelfActivity.this.finish();
			}
		});
        
        gridView = (ShelvesView)findViewById(R.id.grid_shelves);
        
        gridAdapter = new BooksAdapter(this);
        gridView.setAdapter(gridAdapter);
        
        gridView.setOnItemClickListener(mGridItemClickListener);

        
    }
    
    private AdapterView.OnItemClickListener mGridItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent   book_info_intent = new Intent(BookShelfActivity.this, BookInfoActivity.class);
			startActivity(book_info_intent);
        }
    };
    
}