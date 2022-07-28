package com.test.springboot.service.student;

import com.test.springboot.bean.Student;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteById(String id);
    Student queryStudentById(String id);
    Student getStudentById(String id);

    int addJdbcStudent(Student student);
}
