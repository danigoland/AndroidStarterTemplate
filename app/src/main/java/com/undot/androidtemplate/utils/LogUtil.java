package com.undot.androidtemplate.utils;

import android.util.Log;

import com.undot.androidtemplate.BuildConfig;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Created by mymac on 03/06/16.
 */
public class LogUtil {

    private static final int MAX_LOG_LENGTH = 4000;
    private static boolean LOG_FLAG = BuildConfig.DEBUG;
    private static String TAG = "LogUtil";
    public static void i(String tag, @NonNls String msg) {
        if (LOG_FLAG) {
            if(GeneralUtils.isEmpty(tag)){
                tag = TAG;
            }
            if(GeneralUtils.isEmpty(msg)){
                msg = "Empty Message";
            }
            checkedLog(Log.INFO, tag, msg);
        }
    }

    public static void d(String tag, @NonNls String msg) {
        if (LOG_FLAG) {
            if(GeneralUtils.isEmpty(tag)){
                tag = TAG;
            }
            if(GeneralUtils.isEmpty(msg)){
                msg = "Empty Message";
            }
            checkedLog(Log.DEBUG, tag, msg);
        }
    }
    public static void d(String tag, @NonNls String msg, Object... args ) {
        if (LOG_FLAG) {
            if(GeneralUtils.isEmpty(tag)){
                tag = TAG;
            }
            if(GeneralUtils.isEmpty(msg)){
                msg = "Empty Message";
            }
            else{
               msg = String.format(msg,args);
            }
            checkedLog(Log.DEBUG, tag, msg);
        }
    }
    public static void e(String tag, @NonNls String msg) {
        if (LOG_FLAG) {
            if(GeneralUtils.isEmpty(tag)){
                tag = TAG;
            }
            checkedLog(Log.ERROR, tag, msg);
        }
    }
    public static void e(String tag, Exception e) {
        if (LOG_FLAG) {
            if(GeneralUtils.isEmpty(tag)){
                tag = TAG;
            }
            if(!GeneralUtils.isEmpty(e.getMessage())) {
                checkedLog(Log.ERROR,tag,e.getMessage());
            }
            else {
                e.printStackTrace();
            }
        }
    }

    private static void checkedLog(int priority, String tag, String message) {
        if (message.length() < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }
            return;
        }

        // Split by line, then ensure each line can fit into Log's maximum length.
        for (int i = 0, length = message.length(); i < length; i++) {
            int newline = message.indexOf('\n', i);
            newline = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                String part = message.substring(i, end);
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part);
                } else {
                    Log.println(priority, tag, part);
                }
                i = end;
            } while (i < newline);
        }
    }

}
