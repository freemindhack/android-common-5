package com.pheth.android;

import android.os.Bundle;
import android.util.Log;

import com.pheth.android.library.utils.SoftHashMap;

/**
 * Project: android-common
 * Description: com.pheth.android-TestSoftHashMap
 * Author: danhantao
 * Update: danhantao(2015-10-29 18:37)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestSoftHashMap extends PrintLogActivity {
    private static final String TAG = TestSoftHashMap.class.getSimpleName();
    // Only have put,get,containsKey
    private SoftHashMap<String, String> softHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        softHashMap = new SoftHashMap<String, String>();
        softHashMap.put("1", "1");
        softHashMap.put("2", "2");
        Log.i(TAG, "--- " + softHashMap.size() + " ---");
        Log.i(TAG, "---" + softHashMap.get("1"));
        Log.i(TAG, "---" + softHashMap.containsKey("1"));
        outputLog("softHashMap.size:" + softHashMap.size());
        outputLog("softHashMap.get(1):" + softHashMap.get("1"));
        outputLog("softHashMap.get(2):" + softHashMap.get("2"));
    }
}
