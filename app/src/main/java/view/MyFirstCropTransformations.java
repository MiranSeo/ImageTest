package view;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class MyFirstCropTransformations extends BitmapTransformation{

    public MyFirstCropTransformations(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

        Bitmap myBitmap = Bitmap.createBitmap(toTransform, 0, 0, outWidth, outHeight);
        return myBitmap;
    }

    @Override
    public String getId() {
        return "com.the42apps.imageloaders.view";
    }
}
