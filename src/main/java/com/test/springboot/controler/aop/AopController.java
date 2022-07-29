package com.test.springboot.controler.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {
    @RequestMapping(value = "testaop", method = RequestMethod.GET)
    String testAop() {
        System.out.println("AOP");
        return "AOP";
    }
}
