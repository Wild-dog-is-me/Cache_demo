package org.dog.server.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.dog.server.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * desc: <br>单独使用Ehcache配置</br>
 */

@SpringBootTest
class CacheDemoApplicationTests {

    @Test
    public void test1() {
        String absPath = "classpath:ehcache/ehcache.xml";
        // 用来管理多个cache：user_cache | item_cache | store_cache
        CacheManager cacheManager = CacheManager.create(absPath);
        // 获取所有cacheManager管理的cache
        String[] cacheNames = cacheManager.getCacheNames();
        System.out.println(" cacheManager 管理的所有 cache 的名字 ：" + Arrays.asList(cacheNames));
        // 根据cache_name获取对应的cache
        Cache userCache = cacheManager.getCache("user_cache");
        User user = User.builder().id(1L).name("张三").build();
        userCache.put(new Element(user.getId(), user));
        // 通过key取出缓存对象
        Element userElement = userCache.get(user.getId());
        System.out.println("获取到的 userElement ： " + userElement);
        System.out.println("获取到的 userElement 的value ： " + userElement.getObjectValue());

    }

    @Test
    public void test2() {
        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}
