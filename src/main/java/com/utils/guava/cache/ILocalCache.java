package com.utils.guava.cache;

public interface ILocalCache<K, V> {
    /** 
     * 从缓存中获取数据 
     * @param key 
     * @return value 
     */  
    public V get(K key);
}
