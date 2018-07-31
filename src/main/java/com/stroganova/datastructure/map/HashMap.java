package com.stroganova.datastructure.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.75;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        buckets = (ArrayList<Entry<K, V>>[]) new ArrayList[capacity];
    }

    @Override
    public V put(K key, V value) {
        return innerPut(key, value);
    }

    private V innerPut(K key, V value) {
        int bucketIndex = getIndex(key);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>();
        }
        List<Entry<K, V>> innerBucket = buckets[bucketIndex];
        Entry<K, V> entry = getEntry(key);
        V oldValue = null;

            if (entry != null) {
                oldValue = entry.value;
                entry.value = value;
            } else {
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
        for (Entry<K, V> entry : map) {
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
            index = Math.abs(key.hashCode() % buckets.length);
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        ArrayList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new ArrayList[buckets.length * 2 + 1];
        size = 0;
        for (ArrayList<Entry<K, V>> kvEntry : oldBuckets) {
            if(kvEntry != null) {
                for(Entry<K, V> entry : kvEntry) {
                    innerPut(entry.key, entry.value);
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

    static class Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
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