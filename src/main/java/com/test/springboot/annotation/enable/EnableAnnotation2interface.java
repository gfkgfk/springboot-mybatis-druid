package com.test.springboot.annotation.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(Annotation2interfaceConfigSelector.class)
public @interface EnableAnnotation2interface {

}
