package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-06 19:30
 */
public interface OrderMobileService {
    Result save(Map map) throws Exception;

    Map findById(Integer orderId) throws Exception;
}
