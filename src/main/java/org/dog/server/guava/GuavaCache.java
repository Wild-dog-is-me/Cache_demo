package org.dog.server.guava;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;

/**
 * @Author: Odin
 * @Date: 2023/7/10 16:17
 * @Description:
 */

public class GuavaCache implements Cache {
    private String cacheName;
    /**
     * 使用组合模式持有真正的cache对象
     */
    private com.google.common.cache.Cache<Object,Object> internalCache;

    public GuavaCache(String cacheName,com.google.common.cache.Cache<Object,Object> internalCache) {
        this.cacheName = cacheName;
        this.internalCache = internalCache;
    }
    @Override
    public String getName() {
        return cacheName;
    }

    @Override
    public Object getNativeCache() {
        return internalCache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object object = internalCache.getIfPresent(key);
        if (object != null) {
            // 返回ValueWrapper的默认实现
            return new SimpleValueWrapper(object);
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        throw new RuntimeException("这里不实现了,参考get实现");
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new RuntimeException("这里不实现了,参考get实现");
    }

    @Override
    public void put(Object key, Object value) {
        internalCache.put(key,value);
    }

    /**
     * 逐出单个
     * @param key
     */
    @Override
    public void evict(Object key) {
        // 举个例子：这里如果是多级缓存的话，就需要完成本地缓存和分布式缓存的同步逻辑
        // 方法比如通过mq
        internalCache.invalidate(key);
    }

    @Override
    public void clear() {
        internalCache.invalidateAll();
    }
}
