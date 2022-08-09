package com.test.springboot.controller.redis;

import com.test.springboot.bean.RedisBean;
import com.test.springboot.service.redis.RedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "添加redis实例", notes = "添加redis实例测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实例id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名字", required = true, dataType = "String") })
    @RequestMapping(value = "/addredis",method = RequestMethod.POST)
    public RedisBean addRedis(String id, String age, String sex, String name) {
        System.out.println("id:"+id+" age:"+age+" sex:"+sex+" name:"+name);
        RedisBean redisBean = new RedisBean();
        redisBean.setId(Integer.valueOf(id));
        redisBean.setAge(Integer.valueOf(age));
        redisBean.setSex(sex);
        redisBean.setName(name);

        return this.redisService.addRedis(redisBean);
    }

    @RequestMapping(value = "/getredis",method = RequestMethod.POST)
    public RedisBean getRedis(String id){
        System.out.println("getRedis");
        return this.redisService.getRedis(id);
    }

    @RequestMapping(value = "/updateredis", method = RequestMethod.POST)
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
