package com.test.springboot.service.second;

import com.test.springboot.bean.City;
import com.test.springboot.datasource.DataSource;
import com.test.springboot.datasource.DynamicDataSource;
import com.test.springboot.mapper.second.SecondCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("secondCityService")
public class SecondCityServiceImp implements SecondCityService {
    @Autowired
    SecondCityMapper secondCityMapper;

    @Override
    @DataSource("second")
    public City getCityById(String id) {
        System.out.println("设置second完毕");
        return secondCityMapper.getSecondCityById(id);
    }
}
