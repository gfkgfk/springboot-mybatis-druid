package com.test.springboot.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
//public class PropertiesHttpMessageConfig implements WebMvcConfigurer {
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(0,new PropertiesHttpMessageConverter()); //这里添加的队列位置会影响对text/properties的处理优先级
//    }
//}
