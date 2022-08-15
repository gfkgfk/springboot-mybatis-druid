package com.test.springboot.controller.exception;

public class ChildException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String id;

    public ChildException(String id){
        super("Child Exception");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
