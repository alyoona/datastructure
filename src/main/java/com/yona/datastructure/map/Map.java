package com.yona.datastructure.map;

//import java.util.Map

public interface Map<K,V> extends Iterable<HashMap.Entry<K,V>>{
    V put(K key, V value);

    V putIfAbsent(K key, V value);

    void putAll(Map<K, V> map);

    void putAllIfAbsent(Map<K, V> map);

    V get(K key);

    V remove(K key);

    int size();

    boolean containsKey(K key);
    void clear();

}



