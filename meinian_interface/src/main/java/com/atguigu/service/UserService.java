package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author lbstart
 * @create 2021-06-07 18:35
 */
public interface UserService {
    User findUserByUsername(String username);
}
