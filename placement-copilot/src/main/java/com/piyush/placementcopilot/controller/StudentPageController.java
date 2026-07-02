
package com.piyush.placementcopilot.controller;
import jakarta.servlet.http.HttpSession;
import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class StudentPageController {

    @Autowired
    private StudentService studentService;

    // ================= Register =================

    @GetMapping("/register")
    public String registerPage(Model model,
                               HttpSession session) {

        model.addAttribute("student", new Student());

        model.addAttribute("success",
                session.getAttribute("success"));

        model.addAttribute("error",
                session.getAttribute("error"));

        session.removeAttribute("success");
        session.removeAttribute("error");

        return "register";
    }
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student,
                                  HttpSession session) {

        if (studentService.getStudentByEmail(student.getEmail()) != null) {

            session.setAttribute("error",
                    "Email already registered!");

            return "redirect:/register";
        }

        studentService.saveStudent(student);

        session.setAttribute("success",
                "Registration Successful!");

        return "redirect:/login";
    }
    // ================= Login =================

    @GetMapping("/login")
    public String loginPage(Model model,
                            HttpSession session) {

        model.addAttribute("student", new Student());

        model.addAttribute("success",
                session.getAttribute("success"));

        model.addAttribute("error",
                session.getAttribute("error"));

        session.removeAttribute("success");
        session.removeAttribute("error");

        return "login";
    }

    @PostMapping("/login")
    public String loginStudent(@ModelAttribute Student student,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        Student loggedInStudent =
                studentService.loginStudent(
                        student.getEmail(),
                        student.getPassword());

        if (loggedInStudent != null) {

            session.setAttribute("loggedInStudent", loggedInStudent);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Welcome " + loggedInStudent.getName() + "!"
            );

            return "redirect:/dashboard";
        }

        redirectAttributes.addFlashAttribute(
                "error",
                "Invalid Email or Password!"
        );

        return "redirect:/login";
    }

    // ================= Dashboard =================

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session,
                            Model model) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {

            return "redirect:/login";
        }

        model.addAttribute("student", student);

        return "dashboard";
    }

    // ================= Resume Analyzer =================

    @GetMapping("/resume-analyzer")
    public String resumeAnalyzer(HttpSession session,
                                 Model model) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {

            return "redirect:/login";
        }

        model.addAttribute("student", student);

        return "resume-analyzer";
    }

    // ================= Companies =================



    // ================= Applications =================



    // ================= Profile =================

    @GetMapping("/profile")
    public String profile(HttpSession session,
                          Model model) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {

            return "redirect:/login";
        }

        model.addAttribute("student", student);

        return "profile";
    }
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Student updatedStudent,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";
        }

        student.setName(updatedStudent.getName());
        student.setPhone(updatedStudent.getPhone());
        student.setCgpa(updatedStudent.getCgpa());
        student.setBranch(updatedStudent.getBranch());
        student.setGraduationYear(updatedStudent.getGraduationYear());
        student.setUniversity(updatedStudent.getUniversity());
        student.setSkills(updatedStudent.getSkills());
        student.setResumeUrl(updatedStudent.getResumeUrl());

        studentService.updateStudent(student);

        session.setAttribute("loggedInStudent", student);

        redirectAttributes.addFlashAttribute(
                "success",
                "Profile Updated Successfully!"
        );

        return "redirect:/profile";
    }
    // ================= Logout =================

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

}