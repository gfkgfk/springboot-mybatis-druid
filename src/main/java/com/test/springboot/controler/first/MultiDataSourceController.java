package com.test.springboot.controler.first;

import com.test.springboot.bean.City;
import com.test.springboot.bean.Student;
import com.test.springboot.datasource.DynamicDataSourceContextHolder;
import com.test.springboot.service.first.FirstStudentService;
import com.test.springboot.service.second.SecondCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiDataSourceController {
    @Autowired
    FirstStudentService firstStudentService;

    @Autowired
    SecondCityService secondCityService;

    @RequestMapping(value = "/getfirststudent", method = RequestMethod.POST)
    Student getFirstStudent(String id) {
        System.out.println("获取学生信息---First数据库");
        // DynamicDataSourceContextHolder.setDataSourceKey("first");  //用了注解切换，暂时注释掉这里
        return firstStudentService.getFirstStudentById(id);
    }

    @RequestMapping(value = "/secgetcity", method = RequestMethod.POST)
    City getCity(String id) {
        System.out.println("获取城市信息---Second数据库");
        // DynamicDataSourceContextHolder.setDataSourceKey("second");
        return this.secondCityService.getCityById(id);
    }


}
