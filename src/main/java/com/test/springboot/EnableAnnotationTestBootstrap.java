package com.test.springboot;

import com.test.springboot.annotation.enable.EnableAnnotation;
import com.test.springboot.annotation.enable.EnableAnnotation2interface;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableAnnotation  //import引入的配置中的bean，在@EnableAnnotation注解之后，会被发现，类似于SpringBootApplication
@EnableAnnotation2interface //Import来导入接口ImportSelector实现类，该实现类里可以定义需要注册到IOC容器中的组件，以此实现相应模块对应组件的注册
public class EnableAnnotationTestBootstrap {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAnnotationTestBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String enableconfig = context.getBean("enableconfig", String.class);
        System.out.println("enableconfig: " + enableconfig);
        context.close();
    }
}
