package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-04 19:09
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        for (OrderSetting orderSetting : orderSettings) {
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            if(count>0){
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String beginDate = date+"-1";
        String endDate = date+"-31";
        Map<String,Object> mapDate = new HashMap<>();
        mapDate.put("beginDate", beginDate);
        mapDate.put("endDate", endDate);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(mapDate);
        List<Map> mapList = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            HashMap hashMap = new HashMap();
            hashMap.put("date",orderSetting.getOrderDate().getDate());//因为前端展示的是数字类型，所以要使用getDate()方法转一下
            hashMap.put("number",orderSetting.getNumber());
            hashMap.put("reservations",orderSetting.getReservations());
            mapList.add(hashMap);
        }
        return mapList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }

    }
}
