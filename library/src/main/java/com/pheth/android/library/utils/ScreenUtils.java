package com.pheth.android.library.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * Project: android-common
 * Description: com.pheth.android.library.utils-ScreenUtils
 * Author: danhantao
 * Update: danhantao(2015-11-25 17:30)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 *
 */
public class ScreenUtils {
    /**
     * The logical density of the display
     * <p/>
     * for example: nexus 5 density:3  xxhdpi
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return density;
    }

    /**
     * The absolute width of the display in pixels.
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT < 17) {
            //NEXUS 5 DisplayMetrics{density=3.0, width=1080, height=1776, scaledDensity=3.0, xdpi=442.451, ydpi=443.345}
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        } else {
            //NEXUS 5 DisplayMetrics{density=3.0, width=1080, height=1920, scaledDensity=3.0, xdpi=442.451, ydpi=443.345}
            context.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        }
        int width = dm.widthPixels;
        return width;
    }

    /**
     * The absolute height of the display in pixels.
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT < 17) {
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        } else {
            context.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        }
        int height = dm.heightPixels;
        return height;
    }

    /**
     * get statusbar height (px)
     *
     * @param context
     * @return
     */
    public static float getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }


    /**
     * get density
     * <p/>
     * for exapmle Nexus 5 480dp
     *
     * @param ctx
     * @return
     */
    public static int getDensityDpi(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }


}
