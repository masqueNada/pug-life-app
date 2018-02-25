package com.onurdursun.puglife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GridAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] mPugs;

    // 1
    public GridAdapter(Context context, String[] pugs) {
        this.mContext = context;
        this.mPugs = pugs;
    }

    // 2
    @Override
    public int getCount() {
        return mPugs.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.thumbnail_layout, null);
        }

        final ImageView pugView = (ImageView)convertView.findViewById(R.id.imageview_pug);

        /** Picasso library is used because if we manually deal with image bitmaps, we risk
         * BufferOverflow exception. Picasso is a neat way to adapt our grid view with just one
         * line of code.
         */
        Picasso.with(mContext).load(mPugs[position]).into(pugView);

        return convertView;
    }
}
