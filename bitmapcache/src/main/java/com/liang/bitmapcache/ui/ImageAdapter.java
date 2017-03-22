package com.liang.bitmapcache.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liang.bitmapcache.R;
import com.liang.bitmapcache.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liliang on 2017/3/22.
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> mUrlList = new ArrayList<String>();
    private boolean isGridViewIdle = true;
    private boolean mCanGetBitmapFromNetWork = false;
    private int mImageWidth = 0;

    private LayoutInflater mInflater;
    private Drawable mDefaultBitmapDrawable;

    private ImageLoader mImageLoader;

    public ImageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mDefaultBitmapDrawable = context.getResources().getDrawable(R.drawable.image_default);

        mImageLoader = ImageLoader.build(context);
    }

    public void setUrlList(List<String> urlList) {
        mUrlList = urlList;
    }

    public void setGridViewIdle(boolean gridViewIdle) {
        isGridViewIdle = gridViewIdle;
    }

    public void setCanGetBitmapFromNetWork(boolean canGetBitmapFromNetWork) {
        mCanGetBitmapFromNetWork = canGetBitmapFromNetWork;
    }

    public void setImageWidth(int imageWidth) {
        mImageWidth = imageWidth;
    }

    @Override
    public int getCount() {
        return mUrlList.size();
    }

    @Override
    public String getItem(int position) {
        return mUrlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.image_list_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        ImageView imageView = holder.imageView;
        final String tag = (String)imageView.getTag();
        final String uri = getItem(position);
        if (!uri.equals(tag)) {
            imageView.setImageDrawable(mDefaultBitmapDrawable);
        }
        if (isGridViewIdle && mCanGetBitmapFromNetWork) {
            imageView.setTag(uri);
            mImageLoader.bindBitmap(uri, imageView, mImageWidth, mImageWidth);
        }
        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
    }
}
