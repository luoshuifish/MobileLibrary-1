package com.mobilelibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.PersonInformationAdapter;

public class BookInfoActivity extends BaseActivity{
	
    private ListView personInformationListView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_layout);      
        setTopTitle(this, R.string.personal_info);
        setBackButton(this, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BookInfoActivity.this.finish();
			}
		});
        
   
    }

}

