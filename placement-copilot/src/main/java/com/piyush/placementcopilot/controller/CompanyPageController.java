package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.service.ApplicationService;
import com.piyush.placementcopilot.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyPageController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public String companies(HttpSession session,
                            Model model) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";
        }

        model.addAttribute("student", student);

        model.addAttribute("companies",
                companyService.getEligibleCompanies(student.getCgpa()));

        model.addAttribute("success",
                session.getAttribute("success"));

        model.addAttribute("error",
                session.getAttribute("error"));

        session.removeAttribute("success");
        session.removeAttribute("error");

        return "companies";
    }

    @GetMapping("/add")
    public String addCompanyPage(Model model) {

        model.addAttribute("company", new Company());

        return "add-company";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute Company company,
                              HttpSession session) {

        companyService.addCompany(company);

        session.setAttribute("success",
                "Company Added Successfully!");

        return "redirect:/companies";
    }

    @GetMapping("/close/{id}")
    public String closeCompany(@PathVariable Long id,
                               HttpSession session) {

        companyService.closeCompany(id);

        session.setAttribute("success",
                "Company Closed Successfully!");

        return "redirect:/companies";
    }
}