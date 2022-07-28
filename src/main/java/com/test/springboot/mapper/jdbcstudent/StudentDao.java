package com.test.springboot.mapper.jdbcstudent;

import com.test.springboot.bean.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public interface StudentDao {
    public int add(Student student);
}
