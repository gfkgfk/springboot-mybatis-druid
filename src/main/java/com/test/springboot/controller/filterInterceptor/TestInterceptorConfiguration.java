package com.test.springboot.controller.filterInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TestInterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private TestInterceptor testInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor);
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
