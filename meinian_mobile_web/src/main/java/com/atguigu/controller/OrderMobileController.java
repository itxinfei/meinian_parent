package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.service.OrderMobileService;
import com.atguigu.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author lbstart
 * @create 2021-06-06 19:26
 */
@RestController
@RequestMapping("/order")
public class OrderMobileController {

    @Reference
    private OrderMobileService orderMobileService;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){

        try {
            //验证校验码是否正确
            String validateCode = (String)map.get("validateCode");
            String telephone = (String) map.get("telephone");
            String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);

            if(redisCode == null || !redisCode.equals(validateCode)){
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }

            //测试时候可以先注释掉这句话，否则每次点击提交预约，数据库中设置的固定验证码就会删掉，还需要再向数据库中添加固定的 手机号和对应的验证码
            //jedisPool.getResource().del(telephone + RedisMessageConstant.SENDTYPE_ORDER);

            //保存订单
            Result result= orderMobileService.save(map);

            //预约成功，发送短信通知，短信通知内容可以是“预约时间”，“预约人”，“预约地点”，“预约事项”等信息。
            /*String orderDate = (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage(telephone,orderDate);
            } catch (ClientException e) {
                e.printStackTrace();
            }*/
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer orderId){
        try {
            Map map = orderMobileService.findById(orderId);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
