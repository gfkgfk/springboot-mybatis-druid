package com.test.springboot.mapper.second;

import com.test.springboot.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SecondCityMapper {
    City getSecondCityById(@Param("id") String id);
}
