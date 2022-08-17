package com.test.springboot;

import com.test.springboot.service.annotation.TestService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.test.springboot.service.annotation")
public class ServiceBootStrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        TestService testService = context.getBean("testService999", TestService.class);
        System.out.println("TestService Bean: " + testService);
        context.close();
    }
}
