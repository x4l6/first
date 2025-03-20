package com.zut.tlias.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zut.tlias.pojo.Result;
import com.zut.tlias.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
//此注解代表该类要交给ioc容器管理
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //在访问controller之前执行
    //返回true就代表放行
    //返回flase就代表拦截
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //获取请求的url
        String url = req.getRequestURL().toString();
        //判断url中是否含有login，若有，则是访问登录接口,直接放行
        if(url.contains("login")){
            //放行
            log.info("登录操作");

            return true;
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
            return false;
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
            return false;
        }
        log.info("令牌解析成功,放行");
        return true;



    }

    //在访问controller之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //视图渲染完毕执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
