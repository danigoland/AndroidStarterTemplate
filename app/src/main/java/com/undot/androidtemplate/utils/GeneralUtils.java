package com.undot.androidtemplate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.undot.androidtemplate.MyApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import timber.log.Timber;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class GeneralUtils {

    public static String convertTimeStampToDate(long timestamp) {
        try{

            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            boolean isSameDay = DateUtils.isToday(timestamp*1000);
            SimpleDateFormat sdf;
            if(isSameDay)
            {
                sdf = new SimpleDateFormat("h:mm a");

            }
            else
            {
                sdf = new SimpleDateFormat("MM-dd-yyyy h:mm a");

            }
            sdf.setTimeZone(tz);
            Date currenTimeZone = calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }

    public static void openKeyboard(Context context, View view) {

        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void closeKeyboard(Context context, View view) {

        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
    public static void viewGone(View view) {
            if (view != null) {
                view.setVisibility(View.GONE);
            } else {
                Timber.e(MyApp.TAG, "The view is null");
                
            }
        }

    public static void viewInvisible(View view) {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        } else {
            Timber.e(MyApp.TAG, "The view is null");
        }
    }

    public static void viewVisible(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        } else {
            Timber.e(MyApp.TAG, "The view is null");
        }
    }



    public static void showToastInFragment(Fragment fragment, Context context, int messageId) {

        try {
            if (context != null && fragment.isAdded()) {
                Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            //Don't show Toast.
        }

    }

    public static void showToastLong(Context context, String message) {

        try {
            if (context != null) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            //Don't Show toast.
        }

    }
    public static void showToastLong(Context context, int messageId) {

        try {
            if (context != null) {
                Toast.makeText(context, messageId, Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            //Don't Show toast.
        }

    }
    public static void showToastShort(Context context, String message) {

        try {
            if (context != null) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            //Don't Show toast.
        }

    }
    public static void showToastShort(Context context, int messageId) {

        try {
            if (context != null) {
                Toast.makeText(context, messageId, Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            //Don't Show toast.
        }

    }


    public static boolean isFragmentAddedToActivity(Fragment fragment) {

        return fragment.isAdded();

    }


    public static boolean isNetworkConnectionExist(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }







    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }





    public static int getApplicationVersion(Context context) {

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }

    }

    public static void playSound(int resource,Context context){
        MediaPlayer mp = MediaPlayer.create(context, resource);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        mp.start();
    }
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toUpperCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toUpperCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return "IL";
    }

    static Integer[] RandomNumbersWithoutRepetition(Integer start, Integer end, Integer count) {
        Random rng = new Random();

        Integer[] result = new Integer[count];
        Integer cur = 0;
        Integer remaining = end - start;
        for (Integer i = start; i < end && count > 0; i++) {
            Double probability = rng.nextDouble();
            if (probability < (Double.valueOf(count) / Double.valueOf(remaining))) {
                count--;
                result[cur++] = i;
            }
            remaining--;
        }
        return result;
    }

    static String theMonth(Integer month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
    public static boolean isEmpty(String str) {
        return (null == str || str.isEmpty());
    }

    public static boolean isEmpty(Object[] array) {
        return (null == array || array.length == 0);
    }

    public static void setFormattedStringInTextView(Context context, TextView textView, int messageId,Object... args){

        String formattedText = context.getString(messageId,args);
        textView.setText(formattedText);
    }

    public static String arrayListToString(ArrayList<String> list) {
        String strValue = null;
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s + ",");
            strValue = sb.toString();
        }

        if (strValue.length() > 0
                && strValue.charAt(strValue.length() - 1) == ',') {
            strValue = strValue.substring(0, strValue.length() - 1);
        }
        return strValue;
    }

    public static int getRandom(int number) {
        Random rand = new Random();
        return rand.nextInt(number);
    }
    public Location getLastKnownLocation(Context ctx)
    {
        LocationManager locationManager = (LocationManager)ctx.getSystemService("location");
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers)
        {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l != null) {
                if ((bestLocation == null) || (l.getAccuracy() < bestLocation.getAccuracy())) {
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

}
