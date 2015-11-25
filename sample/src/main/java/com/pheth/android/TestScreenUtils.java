package com.pheth.android;

import android.os.Bundle;

import com.pheth.android.library.utils.ScreenUtils;

/**
 * Project: android-common
 * Description: com.pheth.android-TestScreenUtils
 * Author: danhantao
 * Update: danhantao(2015-11-25 17:56)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestScreenUtils extends PrintLogActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int densityDpi = ScreenUtils.getDensityDpi(this);
        outputLog("Dpi dp per inner(120,160,320,480),densityDpi:" + densityDpi);
        final float screenDensity = ScreenUtils.getScreenDensity(this);
        outputLog("screenDensity:" + screenDensity);
        final int screenHeight = ScreenUtils.getScreenHeight(this);
        outputLog("screenHeight:" + screenHeight);
        final int screenWidth = ScreenUtils.getScreenWidth(this);
        outputLog("screenWidth:" + screenWidth);
        final float statusBarHeight = ScreenUtils.getStatusBarHeight(this);
        outputLog("statusBarHeight:" + statusBarHeight);
    }
}
