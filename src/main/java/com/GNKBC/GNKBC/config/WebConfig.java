package com.GNKBC.GNKBC.config;

import com.GNKBC.GNKBC.interceptor.AuthInterceptor;
import com.GNKBC.GNKBC.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/*/css/**","/basicassets/**","/adminassets/**", "/*.ico","/error");

        registry.addInterceptor(new AuthInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/admin","/admin/logout","/admin/loginfail");
   }

}
