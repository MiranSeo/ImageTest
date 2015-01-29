package com.example.miranseo.imagetest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miran Seo on 15. 1. 21.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Images> imageList;
    BaseAdapter base;

    public ImageAdapter(Context c, ArrayList<Images> imgList) {
        mContext = c;
        imageList = imgList;
    }

    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        imageList.get(position);
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {           // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250,250)); // view에 대한 높이와 너비
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 필요하다면 이미지를 가운데쪽으로 잘라냄.
            imageView.setPadding(0, 0, 0, 0); //모든 면을 위한 패딩을 정의
        } else {
            imageView = (ImageView) convertView;
        }
        Glide.with(mContext)
                .fromString()
                .load(imageList.get(position).getPhoto())
                        //adapter에 이미 순서대로 내용 들어가있으므로 해당 위치에 해당하는 것들을 불러와야함.
                .fitCenter()
                .error(R.drawable.ic_launcher)
                .into(iv);
        imageView.setImageURI(Uri.parse(imageList.get(position).getPhoto()));
        notifyDataSetChanged();



        return imageView;
    }

    // references to our images
   // public ArrayList<Images> mThumbIds = new ArrayList<Images>();
    public static ArrayList mThumbIds = new ArrayList();

    //public String mThumbIds[] = null;

}