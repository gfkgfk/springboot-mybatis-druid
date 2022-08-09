package com.test.springboot.service.json;

import com.test.springboot.bean.JsonBean;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("jsonService")
public class JsonServiceImp implements JsonService {

    @Override
    public JsonBean getJson() {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setAge(24);
        jsonBean.setPassword("12321");
        jsonBean.setUserName("hello");
        jsonBean.setBirthday(new Date());
        jsonBean.setSecondBirthday(new Date());
        return jsonBean;
    }
}
