package com.test.springboot.controller.filterInterceptor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterInterceptorController {

    @RequestMapping(value = "/getfi/{id:\\d+}", method = RequestMethod.GET)
    public void fi(@PathVariable String id) {
        System.out.println(id);
    }
}
