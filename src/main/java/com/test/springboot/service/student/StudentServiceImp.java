package com.test.springboot.service.student;

import com.test.springboot.bean.Student;
import com.test.springboot.mapper.jdbcstudent.StudentDao;
import com.test.springboot.mapper.jdbcstudent.StudentDaoImp;
import com.test.springboot.mapper.student.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentDao studentDao; //自动装配的时候会去找实现StudentDao接口的类，如果有两个实现的类就会报错，需要用其他注解解决


    @Override
    public int add(Student student) {
        return this.studentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return this.studentMapper.update(student);
    }

    @Override
    public int deleteById(String id) {
        return this.studentMapper.deleteByIds(id);
    }

    @Override
    public Student queryStudentById(String id) {
        return this.studentMapper.queryStudentById(id);
    }

    @Override
    public Student getStudentById(String id) {
        return this.studentMapper.getStudentById(id);
    }

    @Override
    public int addJdbcStudent(Student student) {
        return studentDao.add(student);
    }


}
