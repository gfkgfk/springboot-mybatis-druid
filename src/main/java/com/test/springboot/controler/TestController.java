package com.test.springboot.controler;

import com.test.springboot.bean.Student;
import com.test.springboot.bean.TestProperties;
import com.test.springboot.bean.TestPropertiesConfigBean;
import com.test.springboot.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestProperties testProperties;
    @Autowired
    TestPropertiesConfigBean testPropertiesConfigBean;

    @RequestMapping("/test")
    String test() {
        System.out.println("test方法");
        return testProperties.getName() + "-----------------" + testProperties.getTitle();
    }

    @RequestMapping("/test2")
    String test2() {
        System.out.println("test2方法");
        return testPropertiesConfigBean.getMessage1() + "-------------" + testPropertiesConfigBean.getMessage2();
    }



}
