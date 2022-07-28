package com.test.springboot.mapper.first;

import com.test.springboot.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FirstStudentMapper {

    Student getFirstStudentById(@Param("id") String id);


}
