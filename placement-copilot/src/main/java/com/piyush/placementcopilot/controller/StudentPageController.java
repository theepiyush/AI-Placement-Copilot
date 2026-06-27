package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentPageController {

    @Autowired
    private StudentService studentService;

    // Open Registration Page
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("student", new Student());

        return "register";
    }

    // Save Student
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/register";
    }

}