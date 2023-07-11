package org.dog.server.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilder;
import net.sf.ehcache.Status;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @Author: Odin
 * @Date: 2023/7/10 15:07
 * @Description:因为spring没有自带的guava_cache的实现，这里自定义
 */

public class GuavaCacheCacheManager extends AbstractCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        /*
            获取所有的cache
         */
        com.google.common.cache.Cache<Object,Object> userCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
        GuavaCache guavaUserCache = new GuavaCache("user_cache", userCache);

        Collection<Cache> caches = new LinkedHashSet<>();
        caches.add(guavaUserCache);
        return caches;
    }
}
