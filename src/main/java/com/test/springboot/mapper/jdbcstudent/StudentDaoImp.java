package com.test.springboot.mapper.jdbcstudent;

import com.test.springboot.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository("StudentDaoImp")
public class StudentDaoImp implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    //用的时候把多数据源去掉...
    @Override
    public int add(Student student) {
        System.out.println("这是第一个");
        String sql = "insert into student(s_no,s_name,s_sex) values(:sno,:name,:sex)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(student));
    }
}
