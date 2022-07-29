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

    @RequestMapping(value = "/getstr", method = RequestMethod.GET)
    private String getstr() {
        System.out.println("getstr:");
        return "getstr";
    }

    @RequestMapping("/test")
    private String test() {
        System.out.println("test方法");
        return this.testProperties.getName() + "-----------------" + this.testProperties.getTitle();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    String test2() {
        System.out.println("test2方法");
        return this.testPropertiesConfigBean.getMessage1() + "-------------" + this.testPropertiesConfigBean.getMessage2();
    }



}
