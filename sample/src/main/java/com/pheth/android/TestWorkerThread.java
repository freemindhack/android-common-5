package com.pheth.android;

import android.os.Bundle;

import com.pheth.android.library.utils.WorkerThread;


/**
 * Project: android-common
 * Description: com.pheth.android-TestWorkerThread
 * Author: danhantao
 * Update: danhantao(2015-10-30 16:35)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestWorkerThread extends PrintLogActivity {
    private WorkerThread sWorkerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sWorkerThread = new WorkerThread();

        sWorkerThread.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                outputLog("run On MainThread:" + Thread.currentThread().getName());
            }
        });
        sWorkerThread.runOnWorkerThread(new Runnable() {
            @Override
            public void run() {
                outputLog("run On WorkerThread:" + Thread.currentThread().getName());
            }
        });


    }
}
