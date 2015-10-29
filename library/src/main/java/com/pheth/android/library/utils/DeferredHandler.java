package com.pheth.android.library.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Pair;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Queue of things to run on a looper thread.  Items posted with {@link #post} will not
 * be actually enqued on the handler until after the last one has run, to keep from
 * starving the thread.
 * <p/>
 * This class is fifo.
 * first in，first out
 */
public class DeferredHandler {
    // Pair <Runnable, Integer>  Integer mean type
    private LinkedList<Pair<Runnable, Integer>> mQueue = new LinkedList<Pair<Runnable, Integer>>();
    private MessageQueue mMessageQueue = Looper.myQueue();
    private Impl mHandler = new Impl();

    private class Impl extends Handler implements MessageQueue.IdleHandler {
        public void handleMessage(Message msg) {
            Pair<Runnable, Integer> p;
            Runnable r;
            synchronized (mQueue) {
                if (mQueue.size() == 0) {
                    return;
                }
                // the removed object
                p = mQueue.removeFirst();
                // get runnable
                r = p.first;
            }
            r.run();
            synchronized (mQueue) {
                scheduleNextLocked();
            }
        }

        public boolean queueIdle() {
            handleMessage(null);
            // remove this message
            return false;
        }
    }

    private class IdleRunnable implements Runnable {
        Runnable mRunnable;

        IdleRunnable(Runnable r) {
            mRunnable = r;
        }

        public void run() {
            mRunnable.run();
        }
    }

    public DeferredHandler() {
    }

    /**
     * Schedule runnable to run after everything that's on the queue right now.
     */
    public void post(Runnable runnable) {
        post(runnable, 0);
    }

    public void post(Runnable runnable, int type) {
        synchronized (mQueue) {
            mQueue.add(new Pair<Runnable, Integer>(runnable, type));
            if (mQueue.size() == 1) {
                scheduleNextLocked();
            }
        }
    }

    /**
     * Schedule runnable to run when the queue goes idle.
     */
    public void postIdle(final Runnable runnable) {
        postIdle(runnable, 0);
    }

    public void postIdle(final Runnable runnable, int type) {
        post(new IdleRunnable(runnable), type);
    }

    public void cancelRunnable(Runnable runnable) {
        synchronized (mQueue) {
            while (mQueue.remove(runnable)) {
            }
        }
    }

    public void cancelAllRunnablesOfType(int type) {
        synchronized (mQueue) {
            ListIterator<Pair<Runnable, Integer>> iter = mQueue.listIterator();
            Pair<Runnable, Integer> p;
            while (iter.hasNext()) {
                p = iter.next();
                if (p.second == type) {
                    iter.remove();
                }
            }
        }
    }

    public void cancel() {
        synchronized (mQueue) {
            mQueue.clear();
        }
    }

    /**
     * Runs all queued Runnables from the calling thread.
     */
    public void flush() {
        LinkedList<Pair<Runnable, Integer>> queue = new LinkedList<Pair<Runnable, Integer>>();
        synchronized (mQueue) {
            queue.addAll(mQueue);
            mQueue.clear();
        }
        for (Pair<Runnable, Integer> p : queue) {
            p.first.run();
        }
    }

    // scheduleNext Lock
    void scheduleNextLocked() {
        if (mQueue.size() > 0) {
            // get next Message
            Pair<Runnable, Integer> p = mQueue.getFirst();
            Runnable peek = p.first;
            if (peek instanceof IdleRunnable) {
//                This may be removed automatically for you by returning false
                mMessageQueue.addIdleHandler(mHandler);
            } else {
                mHandler.sendEmptyMessage(1);
            }
        }
    }
}

