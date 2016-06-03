package com.undot.androidtemplate.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public final class DisplayUtils {

    public static final float CONST_WIDTH = 720f;
    public static final float CONST_DPI = 2f;
    private static Point point;

    public static float calculateEditTextWidth() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        int pxWidth = outMetrics.widthPixels;
        return (float)((pxWidth*0.9375) - (pxWidth / 18) - (pxWidth / 45));
    }

    public static float calculateTextSize() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        int pxWidth = outMetrics.widthPixels;
        return 36 * pxWidth/CONST_WIDTH;
    }

    public static float getScreenWidth() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        return outMetrics.widthPixels;
    }

    public static float getScreenHeight() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        return outMetrics.heightPixels;
    }

    public static float getHeight(Activity activity){
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarTop = rectangle.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int statusbarHeight = Math.abs(statusBarTop - contentViewTop);
        return getScreenHeight() - statusbarHeight;
    }

    public static float calculateDpi() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        return outMetrics.densityDpi/160;
    }

    public static float getStrokeWidth() {
        DisplayMetrics outMetrics = Resources.getSystem().getDisplayMetrics();
        return outMetrics.widthPixels * ((float) 11 / 601);
    }

    public static DisplayMetrics getDisplayMetrics(){
        return Resources.getSystem().getDisplayMetrics();
    }

    public static float dp(){
        DisplayMetrics metrics = getDisplayMetrics();
        return (float) metrics.densityDpi / 160f;
    }

    public static final class FloatPoint {

        public float x;
        public float y;

        public FloatPoint(float pxWidth, float pxHeight) {
            x = pxWidth;
            y = pxHeight;
        }
    }
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    @SuppressLint("NewApi")
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
    public static void changeBrightness(ImageView imageview, float brightness) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        imageview.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

}
