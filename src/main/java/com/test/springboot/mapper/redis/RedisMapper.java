package com.test.springboot.mapper.redis;

import com.test.springboot.bean.RedisBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RedisMapper {

    RedisBean getRedis(@Param("id") String id);

    int updateRedis(RedisBean id);

    int deleteRedis(@Param("id") String id);

}
