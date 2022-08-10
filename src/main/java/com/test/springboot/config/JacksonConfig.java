package com.test.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class JacksonConfig {

//    以下对时间的处理与acturato的httptrace冲突，因此先注释掉

//    @Bean //自定义ObjectMapper,将对象的时间转json时候格式化
//    public ObjectMapper getObjectMapper(){
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        return mapper;
//    }
}
