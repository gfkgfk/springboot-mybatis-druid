package com.test.springboot.service.redis;

import com.test.springboot.bean.RedisBean;
import com.test.springboot.datasource.DataSource;
import com.test.springboot.mapper.first.FirstStudentMapper;
import com.test.springboot.mapper.redis.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImp implements RedisService {

    @Autowired
    RedisMapper redisMapper;

    @Autowired
    FirstStudentMapper firstStudentMapper;


    @Override
    @DataSource("first")
    @CachePut(value = "test", keyGenerator = "myKeyGenerator") //keyGenerator指定缓存key的生成策略
    public RedisBean addRedis(RedisBean redisBean) {
        int id = this.redisMapper.addRedis(redisBean);
        return this.redisMapper.getRedis(String.valueOf(redisBean.getId()));
    }

    @Override
    @DataSource("first")
    @Cacheable(value = "test", key = "#id") //cache注解写在service层
    public RedisBean getRedis(String id) {
        return this.redisMapper.getRedis(id);
    }


    @DataSource("first")
    @Override
    @CachePut(value = "test", key = "#p0.id")
    public RedisBean updateRedis(RedisBean redisBean) {
        this.redisMapper.updateRedis(redisBean);
        return this.redisMapper.getRedis(String.valueOf(redisBean.getId()));
    }


    @DataSource("first")
    @Override
    public int deleteRedis(String id) {
        return this.redisMapper.deleteRedis(id);
    }
}
