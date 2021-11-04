package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author lbstart
 * @create 2021-06-07 18:46
 */
public interface UserDao {
    User findUserByUsername(String username);
}
