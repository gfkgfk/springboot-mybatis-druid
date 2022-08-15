package com.test.springboot.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //增强型controller，对于controller可以做一些全局的处理
public class ControllerExceptionHandler {
    @ExceptionHandler(ChildException.class)//捕获Controller中抛出的不同类型的异常
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //标记返回的http status 错误码
    public Map<String, Object> handleUserNotExistsException(ChildException e) {
        //处理异常
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }
}
