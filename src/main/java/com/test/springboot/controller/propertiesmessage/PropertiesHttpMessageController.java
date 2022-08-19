package com.test.springboot.controller.propertiesmessage;

import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
public class PropertiesHttpMessageController {
    @RequestMapping(value = "/prop",method = RequestMethod.GET) //指定接受媒体类型为text/properties
    @ResponseBody
    public Properties test(@RequestBody Properties properties) {
        System.out.println("PropertiesHttpMessageController---properties:"+properties);
        return properties;
    }


    @RequestMapping(value = "/prop2",method = RequestMethod.GET) //指定接受媒体类型为text/properties
    public Properties test2(@RequestBody Properties properties) {
        System.out.println("PropertiesHandlerMethodArgumentResolver******");
        System.out.println("PropertiesHttpMessageController---properties:"+properties);
        return properties;
    }
}
