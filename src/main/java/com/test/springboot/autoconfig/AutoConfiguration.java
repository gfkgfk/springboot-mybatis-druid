package com.test.springboot.autoconfig;

import com.test.springboot.annotation.enable.EnableAnnotation2interface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAnnotation2interface
@ConditionalOnProperty(name = "helloword",havingValue = "true") //通过与配置文件中对应的属性的值进行比较，如果一致则进行注入，否则不进行注入
public class AutoConfiguration {


}
