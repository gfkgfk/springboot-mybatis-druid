package com.test.springboot.controller.filterInterceptor;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * 过滤器
 */
//@Component
//@WebFilter(urlPatterns = "/*") //第一种配置方式，指定可以进入该过滤器的请求
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始执行过滤器");
        Long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("执行耗时:" + (new Date().getTime() - start));
        System.out.println("执行过滤器结束");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("过滤器销毁");
    }
}
