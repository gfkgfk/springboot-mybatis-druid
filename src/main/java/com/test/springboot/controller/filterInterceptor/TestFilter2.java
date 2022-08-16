package com.test.springboot.controller.filterInterceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//第二种配置filter的方式,通过FilterRegistrationBean来注册过滤器
@Configuration
public class TestFilter2 {
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TestFilter testFilter = new TestFilter();
        filterRegistrationBean.setFilter(testFilter);
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
}
