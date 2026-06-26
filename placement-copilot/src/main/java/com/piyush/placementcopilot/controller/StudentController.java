package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Register Student
    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Login Student
    @PostMapping("/login")
    public String loginStudent(@RequestBody Student student) {

        Student loggedInStudent = studentService.loginStudent(
                student.getEmail(),
                student.getPassword()
        );

        if (loggedInStudent != null) {
            return "Login Successful";
        } else {
            return "Invalid Email or Password";
        }
    }
}