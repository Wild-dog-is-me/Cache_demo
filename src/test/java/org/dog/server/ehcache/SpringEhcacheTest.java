package org.dog.server.ehcache;

import net.sf.ehcache.Element;
import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/7/10 11:33
 * @Description:ehcahe 整合Spring
 */

@ContextConfiguration(value = {"classpath:ehcache/spring-ehcache.xml"})
public class SpringEhcacheTest {

    @Resource
    private CacheManager cacheManager;
    @Resource
    UserService userService;

    /**
     * 注解的方式
     */
    @Test
    public void test2(){
        User user = userService.getUserById(3L);
        System.out.println(user);
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
    }

    /**
     * ehcache与spring集成
     *  AnnotationConfigApplicationContext
     *  xml
     *  使用spring的缓存抽象的方式：
     *      第一种方法：编程式操作spring的缓存抽象api
     *      第二种方法: 注解的方式，如@Cacheable
     *  序列化报错:
     *      第一种方法： <persistence strategy="none"/>
     *      第二种方法： User实现序列化接口
     *
     */
    @Test
    public void test1(){
        Cache userCache = cacheManager.getCache("user_cache");
        /*
         往userCache放入一个user
         */
        User user = User.builder().id(1L).name("张三").build();
        assert userCache != null;
        userCache.put(user.getId(),user);
        // 获取
        User resultUser = userCache.get(1L, User.class);
        System.out.println("获取到结果:"+resultUser);
    }
}
