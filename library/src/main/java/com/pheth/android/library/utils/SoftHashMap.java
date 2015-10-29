package com.pheth.android.library.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftHashMap<K, V> extends HashMap<K, V> {

    private HashMap<K, SoftValue<K, V>> mHashMap;
    private ReferenceQueue<V> queue;

    public SoftHashMap() {
        mHashMap = new HashMap<K, SoftValue<K, V>>();
        queue = new ReferenceQueue<V>();
    }

    @Override
    public V put(K key, V value) {
        SoftValue<K, V> sv = new SoftValue<K, V>(key, value, queue);
        mHashMap.put(key, sv);
        return null;
    }

    @Override
    public V get(Object key) {
        clearSoftReference();
        SoftValue<K, V> sv = mHashMap.get(key);
        if (sv != null) {
            return sv.get();
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * clean recycle Object
     */
    private void clearSoftReference() {
        SoftValue<K, V> poll = (SoftValue<K, V>) queue.poll();
        while (poll != null) {
            mHashMap.remove(poll.key);
            poll = (SoftValue<K, V>) queue.poll();
        }
    }

    /**
     * add key for SoftReference,easy to clean
     *
     * @param <K>
     * @param <V>
     */
    private class SoftValue<K, V> extends SoftReference<V> {
        private Object key;

        public SoftValue(K key, V r, ReferenceQueue<? super V> q) {
            super(r, q);
            this.key = key;
        }
    }

    @Override
    public int size() {
        return mHashMap.size();
    }
}
