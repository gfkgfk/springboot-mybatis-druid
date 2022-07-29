package com.test.springboot.service.first;

import com.test.springboot.bean.Student;
import com.test.springboot.datasource.DataSource;
import com.test.springboot.mapper.first.FirstStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("firstStudentService")
public class FirstStudentServiceImp implements FirstStudentService {
    @Autowired
    FirstStudentMapper firstStudentMapper;

    @Override
    @DataSource("second")
    public Student getFirstStudentById(String id) {
        return this.firstStudentMapper.getFirstStudentById(id);
    }
}
