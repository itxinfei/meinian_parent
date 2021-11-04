package com.atguigu.dao;

import com.atguigu.pojo.Role;

import java.util.Set;

/**
 * @author lbstart
 * @create 2021-06-07 18:48
 */
public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}
