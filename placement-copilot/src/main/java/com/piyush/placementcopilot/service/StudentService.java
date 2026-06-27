package com.piyush.placementcopilot.service;

import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register Student
    public Student saveStudent(Student student) {

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        return studentRepository.save(student);
    }

    // Login Student
    public Student loginStudent(String email, String password) {

        Student student = studentRepository.findByEmail(email);

        if (student != null &&
                passwordEncoder.matches(password, student.getPassword())) {

            return student;
        }

        return null;
    }
}