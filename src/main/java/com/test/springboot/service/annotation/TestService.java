package com.test.springboot.service.annotation;

import com.test.springboot.annotation.FirstLevelService;
import com.test.springboot.annotation.SecondLevelService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SecondLevelService("testService999") //默认名为testService即写为 @FirstLevelService()
@EnableWebMvc
public class TestService {

}
