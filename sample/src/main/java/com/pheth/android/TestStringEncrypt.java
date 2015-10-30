package com.pheth.android;

import android.os.Bundle;

import com.pheth.android.library.utils.StringEncrypt;

/**
 * Project: android-common
 * Description: com.pheth.android-TestStringEncrypt
 * Author: danhantao
 * Update: danhantao(2015-10-30 13:45)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestStringEncrypt extends PrintLogActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        outputLog("StringEncrypt.md5(\"123\"):"+StringEncrypt.md5("123"));;
    }
}
