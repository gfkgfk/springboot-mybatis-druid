package com.test.springboot.service.redis;

import com.test.springboot.bean.RedisBean;
import com.test.springboot.datasource.DataSource;
import com.test.springboot.mapper.first.FirstStudentMapper;
import com.test.springboot.mapper.redis.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImp implements RedisService {

    @Autowired
    RedisMapper redisMapper;

    @Autowired
    FirstStudentMapper firstStudentMapper;


    @Override
    @DataSource("first")
    public RedisBean getRedis(String id) {
        return this.redisMapper.getRedis(id);
    }
    @DataSource("first")
    @Override
    public int updateRedis(RedisBean redisBean) {
        return this.redisMapper.updateRedis(redisBean);
    }


    @DataSource("first")
    @Override
    public int deleteRedis(String id) {
        return this.redisMapper.deleteRedis(id);
    }
}
