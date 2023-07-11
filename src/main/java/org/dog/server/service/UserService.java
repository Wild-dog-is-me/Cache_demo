package org.dog.server.service;

import org.dog.server.model.User;

/**
 * @Author: Odin
 * @Date: 2023/7/10 12:27
 * @Description:
 */
public interface UserService {

    User getUserById(Long id);

    User getUser(User queryParam, int[] args, String str);

}
