package com.test.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


//可以通过实现ApplicationContextInitializer接口用于在Spring Boot应用初始化之前执行一些自定义操作。
@Order(Ordered.HIGHEST_PRECEDENCE)  //可以通过Order注解设置优先级
public class ApplicationContextInitializerTest implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializerTest   id - " + applicationContext.getId());
    }
}
