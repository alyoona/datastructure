package com.stroganova.datastructure.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.75;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = (ArrayList<Entry<K, V>>[]) new ArrayList[capacity];
    }

    @Override
    public V put(K key, V value) {
        int bucketIndex = getIndex(key);

        Entry<K, V> entry = getEntry(key);
        V oldValue = null;
        if (entry != null) {
            oldValue = entry.value;
            entry.value = value;
        } else {
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<>();
            }
            ArrayList<Entry<K, V>> innerBucket = buckets[bucketIndex];
            innerBucket.add(new Entry<>(key, value));
            size++;
        }
        if (size > buckets.length * LOAD_FACTOR) {
            grow();
        }
        return oldValue;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return get(key) != null ? get(key) : put(key, value);
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Entry<K, V> entry : map) {
            put(entry.key, entry.value);
        }
    }

    @Override
    public void putAllIfAbsent(Map<K, V> map) {
        Iterator<Entry<K, V>> mapIterator = map.iterator();
        while (mapIterator.hasNext()) {
            Entry<K, V> entry = mapIterator.next();
            putIfAbsent(entry.key, entry.value);
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> current = getEntry(key);
        return current != null ? current.value : null;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> innerBucket = buckets[index];
        if (innerBucket != null) {
            for (Iterator<Entry<K, V>> iterator = innerBucket.iterator(); iterator.hasNext(); ) {
                Entry<K, V> entry = iterator.next();
                if (key == null ? entry.key == null : key.equals(entry.key)) {
                    V removedValue = entry.value;
                    iterator.remove();
                    size--;
                    return removedValue;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    private int getIndex(K key) {
        int index = 0;
        if (key != null) {
            index = Math.abs(key.hashCode()) % buckets.length;
            /*testLowerBoundOfInteger()*/
            if (index < 0) {
                index = -index;
            }
        }
        return index;
    }

    private void grow() {
        buckets = Arrays.copyOf(buckets, (buckets.length * 2 + 1));
        for (int i = 0; i < buckets.length; i++) {
            ArrayList<Entry<K, V>> bucket = buckets[i];
            if (bucket != null) {
                for (int j = 0; j < bucket.size(); j++) {
                    Entry<K, V> entry = bucket.get(j);
                    if (i != getIndex(entry.key)) {
                        bucket.remove(j);
                        size--;
                        put(entry.key, entry.value);
                    }
                }
                if (bucket.size() == 0) {
                    buckets[i] = null;
                }
            }
        }
    }

    private Entry<K, V> getEntry(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> innerBucket = buckets[index];
        if (innerBucket != null) {
            for (Entry<K, V> entry : innerBucket) {
                if (Objects.equals(key, entry.key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                buckets[i].clear();
            }
            buckets[i] = null;
        }
        size = 0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Entry<K, V>> {
        private int bucketIndex;
        private int count;

        private Iterator<Entry<K, V>> bucketIterator = getNextBucket();

        public boolean hasNext() {
            return count != size;
        }

        public Entry<K, V> next() {
            while (!bucketIterator.hasNext()) {
                bucketIndex++;
                bucketIterator = getNextBucket();
            }
            count++;
            return bucketIterator.next();
        }

        private Iterator<Entry<K, V>> getNextBucket() {
            while (buckets[bucketIndex] == null) {
                bucketIndex++;
            }
            return buckets[bucketIndex].iterator();
        }
    }
}