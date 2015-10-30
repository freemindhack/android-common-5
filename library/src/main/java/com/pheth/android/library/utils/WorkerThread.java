package com.pheth.android.library.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

/**
 * Project: android-common
 * Description: com.pheth.android.library.utils-WorkerThread
 * Author: danhantao
 * Update: danhantao(2015-10-30 16:30)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class WorkerThread {

    private static final HandlerThread sWorkerThread = new HandlerThread("pheth-work");


    private DeferredHandler mHandler = new DeferredHandler();

    static {
        sWorkerThread.start();
    }

    private static final Handler sWorker = new Handler(sWorkerThread.getLooper());


    /**
     * Runs the specified runnable immediately if called from the main thread, otherwise it is
     * posted on the main thread handler.
     */
    public void runOnMainThread(Runnable r) {
        runOnMainThread(r, 0);
    }

    private void runOnMainThread(Runnable r, int type) {
        if (sWorkerThread.getThreadId() == android.os.Process.myTid()) {
            // If we are on the worker thread, post onto the main handler
            mHandler.post(r);
        } else {
            r.run();
        }
    }

    /**
     * Runs the specified runnable immediately if called from the worker thread, otherwise it is
     * posted on the worker thread handler.
     */
    public static void runOnWorkerThread(Runnable r) {
        if (sWorkerThread.getThreadId() == Process.myTid()) {
            r.run();
        } else {
            // If we are not on the worker thread, then post to the worker handler
            sWorker.post(r);
        }
    }


}
