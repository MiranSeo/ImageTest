package com.example.miranseo.showfing;

import java.util.ArrayList;

/**
 * Created by Miran Seo on 15. 1. 31.
 */
public class ImageUtil {
    private static ImageUtil ourInstance = new ImageUtil();

    private static ArrayList<Images> images;
    public static ImageUtil getInstance() {
        return ourInstance;
    }

    private ImageUtil() {
    }

    public static void setImageList(ArrayList<Images> list) {
     images = list;
    }

    public ArrayList<Images> getImages(){
        return images;
    }


}
