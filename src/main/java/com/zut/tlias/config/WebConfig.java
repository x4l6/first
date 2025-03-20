package com.zut.tlias.config;


import com.zut.tlias.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// web配置类
//配置interceptor拦截器


//此注解代表其为配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //要拦截的请求
                .addPathPatterns("/cmd")
                //不需要拦截的请求
                .excludePathPatterns("/login");
    }
}


//第三方bean
@Configuration
 class BeanConfig {

    @Bean
    public LoginInterceptor setBean(){
        return new LoginInterceptor();
    }
}
