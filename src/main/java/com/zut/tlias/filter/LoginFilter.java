package com.zut.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import com.zut.tlias.pojo.Result;
import com.zut.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;



@Slf4j
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, //Request 请求
                         ServletResponse servletResponse, //Response 响应,回应
                         FilterChain filterChain          //filterChain 过滤器链
    ) throws IOException, ServletException {
        //拦截前的操作
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取请求的url
        String url = req.getRequestURL().toString();
        //判断url中是否含有login，若有，则是访问登录接口,直接放行
        if(url.contains("login")){
            //放行
            log.info("登录操作");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //获取请求头token中的令牌
        String jwt = req.getHeader("token");
        //判断有无令牌
        if(StringUtils.hasLength(jwt)){
            log.info("NOT_LOGIN");
            Result err=Result.err("NOT_LOGIN");
            //将notLogin转换为json格式
            String notLogin= JSONObject.toJSONString(err);
            //返回给前端
            resp.getWriter().write(notLogin);
            return;
        }
        //有令牌，开始校验令牌
        try {
            JwtUtils.parseJwtToken(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            //若令牌解析失败，异常会在这里被捕获
            log.info("令牌解析失败");
            Result err=Result.err("NOT_LOGIN");
            //将notLogin转换为json格式
            String notLogin= JSONObject.toJSONString(err);
            //返回给前端
            resp.getWriter().write(notLogin);
            return;
        }
        log.info("令牌解析成功,放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
