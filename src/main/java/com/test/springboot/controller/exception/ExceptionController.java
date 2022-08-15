package com.test.springboot.controller.exception;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exception")
public class ExceptionController {
    @GetMapping("/{id:\\d+}")
    public void getException(@PathVariable String id) {
        throw new RuntimeException("exception: Runtime Exception"+id);
    }


    @GetMapping("/child/{id:\\d+}")
    public void getChildException(@PathVariable String id) {
        throw new ChildException("child exception: Child Exception"+id);
    }

}
