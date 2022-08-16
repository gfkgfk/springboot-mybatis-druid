package com.test.springboot.controller.filterInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TestInterceptor implements HandlerInterceptor {
    @Override //preHandle方法在处理拦截之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("处理拦截之前");
        request.setAttribute("startTime", new Date().getTime());
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override //postHandle只有当被拦截的方法没有抛出异常成功时才会处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("开始处理拦截");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //afterCompletion方法无论被拦截的方法抛出异常与否都会执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理拦截之后");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
        System.out.println("异常信息 " + ex);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
