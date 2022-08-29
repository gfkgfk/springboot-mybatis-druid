package com.test.springboot.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {
    private static final long serialVersionUID = -1L;

    private String from;
    private String message;

    public MessageBean() {
    }

    public MessageBean(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
