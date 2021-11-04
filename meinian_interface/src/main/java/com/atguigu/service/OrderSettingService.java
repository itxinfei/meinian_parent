package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-04 19:09
 */
public interface OrderSettingService {
    void add(List<OrderSetting> orderSettings);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
