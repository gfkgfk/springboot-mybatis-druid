package com.test.springboot.controller.cross;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrossController {
    @RequestMapping(value = "/cross")
    public String index() {
        return "index";
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "this is hello page";
    }
}
