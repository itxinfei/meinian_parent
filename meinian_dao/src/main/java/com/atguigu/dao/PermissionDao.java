package com.atguigu.dao;

import com.atguigu.pojo.Permission;

import java.util.Set;

/**
 * @author lbstart
 * @create 2021-06-07 18:48
 */
public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
