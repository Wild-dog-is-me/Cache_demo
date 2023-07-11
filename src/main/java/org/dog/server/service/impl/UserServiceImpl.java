package org.dog.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/7/10 12:28
 * @Description:
 */

@Slf4j
@Service
@CacheConfig(cacheNames = {"user_cache"})
public class UserServiceImpl implements UserService {

    /**
     * 调用方法:
     *    看缓存有没有？
     *      有: 返回缓存中的结果
     *      没有： 执行方法，并把结果放入缓存
     */
    @Override
    @Cacheable(key = "#id")
    public User getUserById(Long id) {
        log.info("get data from db... id: {}", id);
        return User.builder().id(id).name("dog").build();
    }

    @Override
    @Cacheable
    public User getUser(User queryParam, int[] args, String abc) {
        System.out.println("模拟去db查询--测试自定义KeyGenerator");
        return User.builder().id(5L).name("odin").build();
    }


}
