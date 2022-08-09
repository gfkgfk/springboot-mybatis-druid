package com.test.springboot.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Date;

//@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class) //@JsonNaming，用于指定一个命名策略，作用于类或者属性上。Jackson自带了多种命名策略，你可以实现自己的命名策略，比如输出的key 由Java命名方式转为下面线命名方法 —— userName转化为user-name。
@JsonIgnoreProperties({ "password", "age" }) //忽略一组属性
public class JsonBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private int age;

    @JsonIgnore //忽略属性
    private String password;

    @JsonProperty("bth") //@JsonProperty，作用在属性上，用来为JSON Key指定一个别名
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //@JsonFormat，用于日期格式化
    private Date secondBirthday;


    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getSecondBirthday() {
        return secondBirthday;
    }

    public void setSecondBirthday(Date secondBirthday) {
        this.secondBirthday = secondBirthday;
    }

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

    @Override
    public String toString() {
        return "JsonBean{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", secondBirthday=" + secondBirthday +
                ", info='" + info + '\'' +
                '}';
    }
}
