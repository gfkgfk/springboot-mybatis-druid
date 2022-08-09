package com.test.springboot.controller.student;

import com.test.springboot.bean.Student;
import com.test.springboot.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/querystudent", method = RequestMethod.GET)
    private Student queryStudentById(String id) {
        System.out.println("传递参数:" + id);
        return this.studentService.queryStudentById(id);
    }

    @RequestMapping(value = "/querystudent2", method = RequestMethod.POST)
    private Student queryStudentById2(String id) {
        System.out.println("传递参数2:" + id);
        return this.studentService.queryStudentById(id);
    }


    @RequestMapping(value = "/querystudent3", method = RequestMethod.POST)
    private Student queryStudentById3(String id) {
        System.out.println("传递参数3:" + id);

        return this.studentService.getStudentById(id);
    }
    @RequestMapping(value = "/addjdbcstudent", method = RequestMethod.POST)
    private int addJdbcStudent() {
        Student s = new Student();
        s.setName("GFK");
        s.setSno("888");
        s.setSex("F");
        return this.studentService.addJdbcStudent(s);
    }
}
