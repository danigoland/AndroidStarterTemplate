package com.undot.androidtemplate.utils;

import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class DeviceUtils {


    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su" };
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }
    public static String getDeviceVersion() {

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return String.format("%s %s", manufacturer, model);
        }


    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }
}
