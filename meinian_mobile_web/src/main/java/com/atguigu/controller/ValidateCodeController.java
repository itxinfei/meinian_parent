package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.util.SMSUtils;
import com.atguigu.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author lbstart
 * @create 2021-06-06 18:06
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    //预约时发送手机验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
//        Integer code = ValidateCodeUtils.generateValidateCode(4);//生成4位数字验证码
        try {
            //发送短信
//            SMSUtils.sendShortMessage(telephone,code.toString());
            String code = "1111";
            System.out.println("发送的手机验证码为：" + code);

            //将生成的验证码缓存到redis,用于验证码校验
            //jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 5 * 60, code.toString());

            //验证码发送成功
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_SUCCESS);

        }
    }

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        try {
//            Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
            //SMSUtils.sendShortMessage(telephone,validateCode.toString());

            //jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,5 * 60,validateCode.toString());

            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
