package com.test.springboot.annotation.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableConfig.class)
public @interface EnableAnnotation {

}
