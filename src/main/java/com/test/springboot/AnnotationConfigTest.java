package com.test.springboot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//initializer,listener也可用该文件进行测试

@EnableAutoConfiguration(exclude = {
        DruidDataSourceAutoConfigure.class,
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,}) //需要exclude掉，否则会冲突
//@ComponentScan 注解只能扫描 spring-boot 项目包内的 bean 并注册到 spring 容器中，因此需要 @EnableAutoConfiguration 注解来注册项目包外的bean。而 spring.factories 文件，则是用来记录项目包外需要注册的bean类名。
public class AnnotationConfigTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AnnotationConfigTest.class)
                .web(WebApplicationType.NONE)//Spring Boot入口类中将环境指定为非WEB环境（这样在启动后应用会马上关闭）
                .run(args);
        String enableconfig = context.getBean("enableconfig", String.class);
        System.out.println("enableconfig: " + enableconfig);
        context.close();
    }
}
