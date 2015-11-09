package com.pheth.android;

import android.os.Bundle;

import com.pheth.android.library.utils.AsyncResultHolder;

/**
 * Project: android-common
 * Description: com.pheth.android-TestAsyncResultHolder
 * Author: danhantao
 * Update: danhantao(2015-11-09 20:44)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestAsyncResultHolder extends PrintLogActivity {
    private AsyncResultHolder<Bean> asyncResultHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        outputLog("current time:" + System.currentTimeMillis());
        asyncResultHolder = new AsyncResultHolder<Bean>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bean bean = new Bean("name", 1);
                outputLog("current time:" + System.currentTimeMillis() + " " + Thread.currentThread().getName());
                asyncResultHolder.set(bean);
            }
        }).start();
        outputLog("current time:" + System.currentTimeMillis());
        final Bean bean = asyncResultHolder.get(null, 20000);
        if (bean != null) {
            outputLog("current time:" + System.currentTimeMillis()+" bean:"+bean.toString());
        } else {
            outputLog("current time:" + System.currentTimeMillis()+" bean is null");
        }


    }
}
