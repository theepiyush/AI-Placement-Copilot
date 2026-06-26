package com.piyush.placementcopilot.service;

import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Register Student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Login Student
    public Student loginStudent(String email, String password) {

        Student student = studentRepository.findByEmail(email);

        if (student != null && student.getPassword().equals(password)) {
            return student;
        }

        return null;
    }
}