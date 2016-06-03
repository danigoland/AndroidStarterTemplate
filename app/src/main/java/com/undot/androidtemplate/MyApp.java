package com.undot.androidtemplate;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class MyApp extends Application {
    private static Context context;
    public static String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


        LeakCanary.install(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }



    public static Context getContext() {
        return context;
    }
}

