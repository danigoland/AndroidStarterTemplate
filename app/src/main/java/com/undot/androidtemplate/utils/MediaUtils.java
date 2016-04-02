package com.undot.androidtemplate.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class MediaUtils {

    private static final int SAVE_IMAGE_SIZE = 1000;// was 150
    public static final String LONG_YOUTUBE = "youtube.com/";
    public static final String SHORT_YOUTUBE = "youtu.be/";

    public static String saveBitmap(String filename, Bitmap bitmap){
        float resize = Math.min((float) SAVE_IMAGE_SIZE / bitmap.getWidth(),(float) SAVE_IMAGE_SIZE / bitmap.getHeight());
        saveToFile(getSavePath() + "/"+ filename, Bitmap.createScaledBitmap(bitmap,
                (int) (bitmap.getWidth() * resize), (int) (bitmap.getHeight() * resize),false));
        return getSavePath() + "/"+ filename;
    }

    public static  Bitmap loadBitmap(String filename){
        return loadFromFile(getSavePath() + "/" + filename);
    }

    public static File getSavePath() {
        File path;
        if (hasSDCard()) { // SD card
            path = new File(getSDCardPath() + "/remote/");
            path.mkdir();
        } else {
            path = Environment.getDataDirectory();
        }
        return  path;
    }

    public static String getSavePathLoader(){
        return "file:///" + getSavePath() + "/";
    }

    public static String getCacheFilename() {
        File f = getSavePath();
        return f.getAbsolutePath() + "/cache.png";
    }

    public static Bitmap loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return null;
            }
            Bitmap tmp = BitmapFactory.decodeFile(filename);
            return tmp;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap loadFromCacheFile() {
        return loadFromFile(getCacheFilename());
    }

    public static void saveToCacheFile(Bitmap bmp) {
        saveToFile(getCacheFilename(), bmp);
    }

    public static void saveToFile(String filename, Bitmap bmp) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    public static boolean hasSDCard() { // SD????????
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getSDCardPath() {
        File path = Environment.getExternalStorageDirectory();
        return path.getAbsolutePath();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bitmap.getWidth()/8;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getSquareBitmap(Bitmap bitmap){
        Bitmap output;
        if (bitmap.getWidth() >= bitmap.getHeight())
            output = Bitmap.createBitmap(bitmap, bitmap.getWidth() / 2 - bitmap.getHeight() / 2, 0, bitmap.getHeight(), bitmap.getHeight());
        else
            output = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - bitmap.getWidth() / 2, bitmap.getWidth(), bitmap.getWidth());
        return output;
    }


    public static Drawable getDrawableByName(String name, Context context ) {
        int drawableResource = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        if ( drawableResource == 0 ) {
            throw new RuntimeException("Can't find drawable with name: " + name );
        }
        return context.getResources().getDrawable(drawableResource);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleHeight* 1.2f, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
    public static Bitmap drawableTobitmap(Context mContext, int drawable) {
        // TODO Auto-generated method stub
        Drawable myDrawable = mContext.getResources().getDrawable(drawable);
        return ((BitmapDrawable) myDrawable).getBitmap();
    }
    public static Drawable bitmapToDrawable(Context mContext, Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap convertBase64toBitmap(String base64String) {

        if (base64String != null && !base64String.equals("")) {

            byte[] imageData = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        } else
            return null;
    }
    public static String encodeBitmapToBase64(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);

    }
    public static Bitmap byteArrayToBitmap(byte[] array) {
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] array = stream.toByteArray();
        stream.close();
        return array;


    }


    public static boolean isYoutubeURL(String url) {
        if(url.contains(SHORT_YOUTUBE)||url.contains(LONG_YOUTUBE))
            return true;
        return false;
    }



    public static String getYoutubeVideoId(String url){
        String youtubeId = "";
        if (isYoutubeURL(url)){
            if (url.contains(LONG_YOUTUBE)){
                try{
                    Uri uri = Uri.parse(url);
                   youtubeId = uri.getQueryParameter("v");
                }
                catch (Exception e){
                    return null;
                }
            }
            else if (url.contains(SHORT_YOUTUBE)){
                try{
                    url = url.split(SHORT_YOUTUBE)[1];
                    if (url.contains("?")){
                        url = url.split("/?")[0];
                    }
                    youtubeId = url;
                }
                catch (Exception e){
                    return null;
                }
            }
        }
        return youtubeId;
    }

}
