package com.test.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class TestPropertiesConfigBean {
    private String message1;
    private String message2;

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    @Override
    public String toString() {
        return "TestPropertiesConfigBean{" +
                "message1='" + message1 + '\'' +
                ", message2='" + message2 + '\'' +
                '}';
    }
}
