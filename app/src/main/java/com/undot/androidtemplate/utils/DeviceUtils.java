package com.undot.androidtemplate.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<App> getInstalledApp(Context context)
    {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(128);
        Intent mainIntent = new Intent("android.intent.action.MAIN", null);
        mainIntent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> pkgAppsList = pm.queryIntentActivities(mainIntent, 0);
        List<App> apps = new ArrayList(pkgAppsList.size());
        Set<String> apsNames = new HashSet(pkgAppsList.size());
        String s;
        for (ResolveInfo resInfo : pkgAppsList)
        {
            String resName = resInfo.activityInfo.name;String resPackage = resInfo.activityInfo.packageName;
            for (ApplicationInfo appInfo : packages)
            {
                String appPackage = appInfo.packageName;

                    apps.add(new App(appPackage, appInfo.flags));
                    apsNames.add(appPackage);
                    break;

            }
            s = resName;
        }

        return apps;
    }
    private class App
    {
        private String name;
        private int flags;

        public App(String name, int flags)
        {
            this.name = name;
            this.flags = flags;
        }

        public String getName()
        {
            return this.name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getFlags()
        {
            return this.flags;
        }

        public void setFlags(int flags)
        {
            this.flags = flags;
        }

        public String toString()
        {
            return "{name:'" + this.name + '\'' + ", flags:" + this.flags + '}';
        }
    }
}
