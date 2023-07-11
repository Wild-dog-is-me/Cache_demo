package org.dog.server.guava;

import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/7/10 14:59
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:guava/spring-guava-cache.xml"})
public class SpringGuavaCacheTest {

    @Resource
    private CacheManager cacheManager;

    @Resource
    private UserService userService;

    /**
     * 测试自定义CacheManager
     * 1. 编写 GuavaCacheCacheManager
     * 2. 编写 GuavaCache
     * 3. 配置spring-guava-cache.xml
     */
    @Test
    public void test1() {
        System.out.println(cacheManager.getClass());
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
    }

    @Test
    public void test2(){
        User queryParam = User.builder().id(5L).name("odin").build();
        queryParam.setId(5L);
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 2;
        User user = userService.getUser(queryParam,arr,"abc");
        System.out.println(user);
        System.out.println(userService.getUser(queryParam,arr,"abc"));
        System.out.println(userService.getUser(queryParam,arr,"abc"));
    }
}
