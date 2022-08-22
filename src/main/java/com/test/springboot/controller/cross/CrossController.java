package com.test.springboot.controller.cross;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//临时修改本地hosts文件    --->   127.0.0.1       test.kent.cc
//模拟跨域
@Controller
public class CrossController {
    @RequestMapping(value = "/cross")
    public String index() {
        return "index";
    }

    @RequestMapping("hello")
    @ResponseBody
    @CrossOrigin(value = "*") //注解该接口可跨域
    public String hello() {
        return "this is hello page";
    }


    //此外，也可通过实现WebMvcConfigurer接口来实现、通过过滤器来实现、Actuator来实现。
}
