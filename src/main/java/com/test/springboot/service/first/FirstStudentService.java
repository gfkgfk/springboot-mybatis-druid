package com.test.springboot.service.first;

import com.test.springboot.bean.Student;
import org.springframework.stereotype.Service;

public interface FirstStudentService {
    public Student getFirstStudentById(String id);

}
