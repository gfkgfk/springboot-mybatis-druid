package com.test.springboot.controller.hotdeploy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotDeployController {
    @RequestMapping(value = "hot",method = RequestMethod.POST)
    public String getHotDeployStr(){
        System.out.println("double----");
        return "double";

    }
}
