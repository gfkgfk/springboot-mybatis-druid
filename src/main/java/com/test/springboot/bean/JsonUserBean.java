package com.test.springboot.bean;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;

//@JsonView，作用在类或者属性上，用来定义一个序列化组。 比如对于User对象，某些情况下只返回userName属性就行，而某些情况下需要返回全部属性。 因此User对象可以定义成如下：
public class JsonUserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public interface UserNameView {};
    public interface AllUserFieldView extends UserNameView {};

    @JsonView(UserNameView.class)
    private String userName;

    @JsonView(AllUserFieldView.class)
    private int age;

    @JsonView(AllUserFieldView.class)
    private String password;

    @JsonView(AllUserFieldView.class)
    private Date birthday;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
