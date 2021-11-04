package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-06 19:31
 */
public interface OrderMobileDao {

    Map findById(Integer orderId);

    List<Order> findByCondition(Order order);

    void add(Order order);

    int getTodayOrderNumber(String today);

    int getTodayVisitsNumber(String today);

    int getThisWeekAndMonthOrderNumber(Map<String, Object> paramWeek);

    int getThisWeekAndMonthVisitsNumber(Map<String, Object> paramWeekVisit);

    List<Map<String, Object>> findHotSetmeal();

}
