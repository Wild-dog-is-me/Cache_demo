package org.dog.server.guava;

import com.google.common.cache.*;
import org.dog.server.model.User;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Odin
 * @Date: 2023/7/10 12:53
 * @Description:
 */
public class LoadingCacheTest {
    /**
     * 单独使用guava的cache，guava的cache分为两种:
     *      第一种: Cache<---LoadingCache<---com.google.common.cache.LocalCache.LocalLoadingCache
     *              特点: 缓存中获取不到值，会根据指定的loader进行加载，加载后自动放入缓存
     *      第二种: Cache<---com.google.common.cache.LocalCache.LocalManualCache
     *              特点：类似ehcache
     */
    @Test
    public void test1() throws InterruptedException {
        LoadingCache<Long, User> loadingCache = CacheBuilder.newBuilder()
                // 指定并发级别
                .concurrencyLevel(8)
                // 初始化大小,配合concurrencyLevel做分段锁
                .initialCapacity(60)
                // 缓存中最多可放多少个元素
                .maximumSize(10)
                // 从写入开始计算，10s过期
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // 统计命中率
                .recordStats()
                // 缓存中的元素被驱逐出去后会自动回调到这里
                .removalListener(new RemovalListener<Long, User>() {
                    @Override
                    public void onRemoval(RemovalNotification<Long, User> notification) {
                        Long key = notification.getKey();
                        RemovalCause cause = notification.getCause();
                        System.out.println("key:"+key+"被移出缓存,原因:"+cause);
                    }
                })
                // 缓存中获取不到值，会回调到这里
                .build(new CacheLoader<Long, User>() {
                    // key:将来loadingCache.get(k)获取不到传来的k
                    @Override
                    public User load(Long key) throws Exception {
                        // 可以在这里就行数据的加载
                        System.out.println("去存储中加载");
                        return User.builder().id(key).name("张三").build();
                    }
                });
        for (long i = 0; i < 20; i++) {
            User user = loadingCache.getUnchecked(10L);
            System.out.println(user);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(loadingCache.stats().toString());

    }
}
