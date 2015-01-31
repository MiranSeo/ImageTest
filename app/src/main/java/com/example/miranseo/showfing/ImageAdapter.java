//package com.example.miranseo.imagetest;
//
//import android.content.Context;
//import android.net.Uri;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import Images;
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import view.ViewHolder;
//
///**
// * Created by Miran Seo on 15. 1. 21.
// */
//public class ImageAdapter extends ArrayAdapter<String> {
////    private Images images;
////    private final LayoutInflater mInflater;
////    private int mResource;
////
////    public ImageAdapter(Context context, int resource) {
////        super(context, resource);
////        mResource = resource;
////        mInflater = LayoutInflater.from(context);
////    }
////
//////    public ImageAdapter(Context context, int resource, ArrayList<Images.Image> images) {
//////        super(context, resource);
//////        mResource = resource;
//////        mInflater = LayoutInflater.from(context);
//////    }
////
////    public void setPhoto(Images images){
////
////        this.images = images;
////    }
//private final LayoutInflater mInflater;
//    private int mResource;
//
//    public ImageAdapter(Context context, int resource) {
//        super(context, resource);
//        mResource = resource;
//        mInflater = LayoutInflater.from(context);
//    }
//
//
//    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = mInflater.inflate(mResource, parent, false);
//        }
//
//        ImageView imageView = ViewHolder.get(convertView, R.id.image);
////
////
////
////
////        ImageView imageView;
////        if (convertView == null) {           // if it's not recycled, initialize some attributes
////            imageView = new ImageView(mContext);
////            imageView.setLayoutParams(new GridView.LayoutParams(250,250)); // view에 대한 높이와 너비
////            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 필요하다면 이미지를 가운데쪽으로 잘라냄.
////            imageView.setPadding(0, 0, 0, 0); //모든 면을 위한 패딩을 정의
////        } else {
////            imageView = (ImageView) convertView;
////        }
//        String url = getItem(position);
//        Glide.with(getContext())
//
//                        .fromString()
//                        .load(url)
//                        //adapter에 이미 순서대로 내용 들어가있으므로 해당 위치에 해당하는 것들을 불러와야함.
//                        .fitCenter()
//                        .error(R.drawable.ic_launcher)
//                        .thumbnail(0.1f)
//                        .into(imageView);
//        //imageView.setImageURI(Uri.parse(imageList.get(position).getPhoto()));
//        //notifyDataSetChanged();
//
//
//
//        return imageView;
//    }
//
//    // references to our images
//   // public ArrayList<Images> mThumbIds = new ArrayList<Images>();
// //   public static ArrayList mThumbIds = new ArrayList();
//
//    //public String mThumbIds[] = null;
//
//}

package com.example.miranseo.showfing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Miran Seo on 15. 1. 21.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Images> imageList;

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

        //String url = getItem(position).toString();
        String url = imageList.get(position).getPhoto();
        Glide.with(mContext)

                        .fromString()
                        .load(url)
                        //adapter에 이미 순서대로 내용 들어가있으므로 해당 위치에 해당하는 것들을 불러와야함.
                        .fitCenter()
                        .error(R.drawable.ic_launcher)
                        .thumbnail(0.1f)
                        .into(imageView);

        //imageView.setImageURI(Uri.parse(imageList.get(position).getPhoto()));
        notifyDataSetChanged();



        return imageView;
    }

    // references to our images
    // public ArrayList<Images> mThumbIds = new ArrayList<Images>();
    public static ArrayList mThumbIds = new ArrayList();

    //public String mThumbIds[] = null;

}