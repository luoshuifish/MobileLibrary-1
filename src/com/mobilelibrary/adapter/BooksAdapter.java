

package com.mobilelibrary.adapter;

import android.widget.CursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AbsListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.database.CharArrayBuffer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.mobilelibrary.R;

import com.mobilelibrary.activity.BookShelfActivity;
import com.mobilelibrary.dao.BaseDAO;
import com.mobilelibrary.drawable.CrossFadeDrawable;
import com.mobilelibrary.drawable.FastBitmapDrawable;
import com.mobilelibrary.utils.ImageUtilities;

public class BooksAdapter extends CursorAdapter {
	
	
    private static final String[] PROJECTION_IDS_AND_TITLE = new String[] {
    	BaseDAO.bookId, BaseDAO.bookName
    };

    private final LayoutInflater mInflater;

    private final int mBookIdIndex;
    
    private final int mBookNameIndex;

    
    private final BookShelfActivity mActivity;
    private final Bitmap mDefaultCoverBitmap;
    private final FastBitmapDrawable mDefaultCover;


    public BooksAdapter(BookShelfActivity activity) {
        super(activity, activity.managedQuery(BaseDAO.URI_STORED_BOOK,
                PROJECTION_IDS_AND_TITLE,
                null, null, null), true);

        final Cursor c = getCursor();

        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mBookIdIndex = c.getColumnIndexOrThrow(BaseDAO.bookId);
        mBookNameIndex = c.getColumnIndexOrThrow(BaseDAO.bookName);

        mDefaultCoverBitmap = BitmapFactory.decodeResource(activity.getResources(),
                R.drawable.unknown_cover);
        mDefaultCover = new FastBitmapDrawable(mDefaultCoverBitmap);

    }

    FastBitmapDrawable getDefaultCover() {
        return mDefaultCover;
    }
    
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final TextView view = (TextView) mInflater.inflate(R.layout.shelf_book, parent, false);

        ViewHolder holder = new ViewHolder();
        holder.title = view;

        view.setTag(holder);

        final CrossFadeDrawable transition = new CrossFadeDrawable(mDefaultCoverBitmap, null);
        transition.setCallback(view);
        transition.setCrossFadeEnabled(true);
        holder.transition = transition;

        return view;
    }

    public void bindView(View view, Context context, Cursor c) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String bookId = c.getString(mBookIdIndex);
        holder.bookId = bookId;
        holder.bookName = c.getString(mBookNameIndex);
        final BookShelfActivity activity = mActivity;
//        holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
//                    ImageUtilities.getCachedCover(bookId, mDefaultCover));
        Resources res = context.getResources();
        Bitmap bmp=BitmapFactory.decodeResource(res, R.drawable.book_recommend_default);
        Matrix matrix = new Matrix();
        matrix.postScale(0.7f,0.7f); 
        Bitmap resizeBmp = bmp.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
        holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
               new FastBitmapDrawable(resizeBmp));
       
        final CharArrayBuffer buffer = holder.buffer;
        c.copyStringToBuffer(mBookNameIndex, buffer);
        final int size = buffer.sizeCopied;
        if (size != 0) {
            holder.title.setText(buffer.data, 0, size);
        }
    }

    @Override
    public void changeCursor(Cursor cursor) {
        final Cursor oldCursor = getCursor();
        if (oldCursor != null) mActivity.stopManagingCursor(oldCursor);
        super.changeCursor(cursor);
    }

	class ViewHolder {
	    TextView title;
	    String bookId;
	    CrossFadeDrawable transition;
	    final CharArrayBuffer buffer = new CharArrayBuffer(64);
	    boolean queryCover;
	    String bookName;
	}
}


