package com.test.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

public class ApplicationContextInitializerTestLow implements ApplicationContextInitializer,Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializerTestLow  id - " + applicationContext.getId());
    }

    @Override //也可以通过实现Ordered接口进行优先级设置
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
