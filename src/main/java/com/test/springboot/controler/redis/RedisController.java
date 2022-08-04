package com.test.springboot.controler.redis;

import com.sun.corba.se.impl.ior.FreezableList;
import com.test.springboot.bean.RedisBean;
import com.test.springboot.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisService redisService;


    @RequestMapping(value = "/getredis",method = RequestMethod.POST)
    public RedisBean getRedis(String id){
        System.out.println("getRedis");
        return this.redisService.getRedis(id);
    }

    @RequestMapping(value = "/updateredis", method = RequestMethod.POST)
//    @CachePut()
    RedisBean updateRedis(String id){
        System.out.println("updateredis");
        RedisBean redisBean = new RedisBean();
        redisBean.setId(Integer.valueOf(id));
        redisBean.setAge(190);
        redisBean.setSex("M");
        redisBean.setName("测试Redis");
        return this.redisService.updateRedis(redisBean);
    }

    @RequestMapping(value = "/deleteredis", method = RequestMethod.POST)
    int deleteRedis(String id){
        System.out.println("deleteredis");
        return this.redisService.deleteRedis(id);
    }



}
