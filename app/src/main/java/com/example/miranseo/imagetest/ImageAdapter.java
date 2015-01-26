package com.example.miranseo.imagetest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miran Seo on 15. 1. 21.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {           // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350,250)); // view에 대한 높이와 너비
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 필요하다면 이미지를 가운데쪽으로 잘라냄.
            imageView.setPadding(0, 0, 0, 0); //모든 면을 위한 패딩을 정의
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageURI(Uri.parse((mThumbIds.get(position).getPhoto())));
       // imageView.setImageURI(Uri.parse((mThumbIds.get(position))));


        return imageView;
    }

    // references to our images
    public ArrayList<Images> mThumbIds = new ArrayList<Images>();
   // public ArrayList<String> mThumbIds = new ArrayList<>();

}