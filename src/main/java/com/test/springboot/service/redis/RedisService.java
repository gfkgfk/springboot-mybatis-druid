package com.test.springboot.service.redis;

import com.test.springboot.bean.RedisBean;
import com.test.springboot.datasource.DataSource;

public interface RedisService {

    RedisBean getRedis(String id);

    int updateRedis(RedisBean redisBean);
    int deleteRedis(String id);

}
