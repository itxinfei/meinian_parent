package com.atguigu.security;

import com.alibaba.fastjson.JSON;
import com.atguigu.entity.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lbstart
 * @create 2021-06-07 21:00
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        if (isAjaxRequest(request)) {// AJAX请求,使用response发送403
            Result result = new Result(false, "无权访问", "403");
            String json = JSON.toJSONString(result);
            response.getWriter().print(json);
        } else {// 同步请求处理
            request.getRequestDispatcher("/pages/error/403.html").forward(request, response);
        }
    }
}
