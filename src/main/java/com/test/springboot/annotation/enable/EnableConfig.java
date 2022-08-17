package com.test.springboot.annotation.enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnableConfig {
    @Bean("enableconfig")
    public String hello() {
        return "enable config";
    }
}
