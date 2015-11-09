package com.pheth.android.library.utils;

/**
 * Project: android-common
 * Description: com.pheth.android.library.utils-AsyncResultHolder
 * Author: danhantao
 * Update: danhantao(2015-11-09 20:38)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * This class is a holder of the result of an asynchronous computation.
 *
 * @param <E> the type of the result.
 */
public class AsyncResultHolder<E> {

    private final Object mLock = new Object();

    private E mResult;
    private final CountDownLatch mLatch;

    public AsyncResultHolder() {
        mLatch = new CountDownLatch(1);
    }

    /**
     * Sets the result value of this holder.
     *
     * @param result the value to set.
     */
    public void set(final E result) {
        synchronized (mLock) {
            if (mLatch.getCount() > 0) {
                mResult = result;
                mLatch.countDown(); // -1
            }
        }
    }

    /**
     * Gets the result value held in this holder.
     * Causes the current thread to wait unless the value is set or the specified time is elapsed.
     *
     * @param defaultValue the default value.
     * @param timeOut      the maximum time to wait.
     * @return if the result is set before the time limit then the result, otherwise defaultValue.
     */
    public E get(final E defaultValue, final long timeOut) {
        try {
            // ms
            if (mLatch.await(timeOut, TimeUnit.MILLISECONDS)) {
                return mResult;
            } else {
                return defaultValue;
            }
        } catch (InterruptedException e) {
            return defaultValue;
        }
    }
}
