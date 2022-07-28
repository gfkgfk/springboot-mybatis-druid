package com.test.springboot.mapper.student;

import com.test.springboot.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StudentMapper {
    @Insert("Insert into student(s_no,s_name,s_sex) values('999', 'John', 'M ' )")
    int add(Student student);

    int update(Student student);

    int deleteByIds(String id);

    @Select("select * from student where id=#{id}")
    @Results(value= {
            @Result(property = "sno", column = "s_no", javaType = String.class),
            @Result(property = "name", column = "s_name", javaType = String.class),
            @Result(property = "sex", column = "s_sex", javaType = String.class)
    })
    Student queryStudentById(String id);

    Student getStudentById(@Param("id") String id);


}
