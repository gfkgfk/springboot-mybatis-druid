package com.test.springboot.service.redis;

import com.test.springboot.bean.RedisBean;


public interface RedisService {

    RedisBean getRedis(String id);

    RedisBean updateRedis(RedisBean redisBean);

    int deleteRedis(String id);

}
