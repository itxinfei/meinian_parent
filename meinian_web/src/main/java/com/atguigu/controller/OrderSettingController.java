package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.service.SetmealService;
import com.atguigu.util.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-04 19:08
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] strings : list) {
                OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                orderSettings.add(orderSetting);
            }
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        List<Map> list =orderSettingService.getOrderSettingByMonth(date);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }

    @RequestMapping("editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){  //虽然前端虽然只传过来两个参数（orderDate、number）,但是
        try {                                                                //当使用@ResponseBody注解 springmvc在使用OrderSetting对象接收参数时，会使用反射
            orderSettingService.editNumberByDate(orderSetting);                //创建bean对象，哪个参数没有赋值就会使用默认赋值。
            return  new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

}
