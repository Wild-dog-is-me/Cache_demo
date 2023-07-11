package org.dog.server.ehcache;

import org.dog.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: Odin
 * @Date: 2023/7/10 12:44
 * @Description:ehcahe 整合SpringBoot
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEhcacheTest {

    /**
     * ehcache与springboot集成
     *  1.在application.yml中指定ehcache的配置文件
     *  2.@EnableCaching
     */

    @Resource
    private CacheManager cacheManager;

    @Resource
    private UserService userService;

    @Test
    public void test1() {
        System.out.println(cacheManager.getClass());
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
        System.out.println(userService.getUserById(3L));
    }
}
