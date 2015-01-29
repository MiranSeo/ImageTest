package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class MyBlueTransformations extends BitmapTransformation{

    Paint bluePaint;

    public MyBlueTransformations(Context context) {
        super(context);
        bluePaint = new Paint();
        bluePaint.setColorFilter(
                new PorterDuffColorFilter(Color.argb(180,30,30,180), PorterDuff.Mode.DARKEN));
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

        Canvas canvas = new Canvas(toTransform);
        canvas.drawBitmap(toTransform, 0, 0, bluePaint);
        return toTransform;
    }

    @Override
    public String getId() {
        return "com.the42apps.imageloaders.view";
    }
}
