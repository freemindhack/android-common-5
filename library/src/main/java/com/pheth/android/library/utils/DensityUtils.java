package com.pheth.android.library.utils;

import android.content.Context;

/**
 * Project: android-common
 * Description: com.pheth.android.library.utils-DensityUtils
 * Author: danhantao
 * Update: danhantao(2015-11-25 17:25)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class DensityUtils {
    /**
     * dip 2 px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px 2 dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * px 2 sp
     *
     * @param px
     * @return sp
     */
    public static int px2sp(Context context, int px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity);
    }

    /**
     * sp 2 px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context, int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity);
    }


}
