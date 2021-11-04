package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lbstart
 * @create 2021-06-07 19:30
 */
@Service(interfaceClass = LoginService.class)
@Transactional
public class LoginServiceImpl implements LoginService {

}
