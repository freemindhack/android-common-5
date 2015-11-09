package com.pheth.android;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;

import com.pheth.android.library.utils.DeferredHandler;

/**
 * Project: android-common
 * Description: com.pheth.android-TestDeferredHandler
 * Author: danhantao
 * Update: danhantao(2015-10-29 16:39)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class TestDeferredHandler extends PrintLogActivity {
    private static final String TAG = TestDeferredHandler.class.getSimpleName();
    DeferredHandler deferredHandler = new DeferredHandler();
    // DeferedHandler 1.excute main 2.queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deferredHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "3.main " + Thread.currentThread().getName());
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "1.new Thread " + Thread.currentThread().getName());
                deferredHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "4.deferred Handler(1)" + Thread.currentThread().getName());
                    }
                });
            }
        }).start();
        deferredHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "deferred Handler " + Thread.currentThread().getName());
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "2.new Thread " + Thread.currentThread().getName());
                deferredHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "5.deferred Handler(2)" + Thread.currentThread().getName());
                    }
                });
            }
        }).start();

        // Idle handler
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.i(TAG, "queueIde");
                return false;
            }
        });

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
//                Log.i(TAG,"queueIde continue");
                outputLog("queueIde continue");
                return true;
            }
        });

    }
}
