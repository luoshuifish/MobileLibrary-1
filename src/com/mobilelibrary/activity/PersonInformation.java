package com.mobilelibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.mobilelibrary.R;
import com.mobilelibrary.adapter.PersonInformationAdapter;

public class PersonInformation  extends BaseActivity{
	
    private ListView personInformationListView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_imfomation);
        
        setTopTitle(this, R.string.personal_info);
        setBackButton(this, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PersonInformation.this.finish();
			}
		});
        
        personInformationListView = (ListView) this.findViewById(R.id.lv_other);
		personInformationListView.setAdapter(new PersonInformationAdapter(this));
    }

}
