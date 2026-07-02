package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Application;
import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.service.ApplicationService;
import com.piyush.placementcopilot.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationPageController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/apply/{companyId}")
    public String applyJob(@PathVariable Long companyId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";
        }

        Company company = companyService.getCompanyById(companyId);

        // Already Applied Check
        if (applicationService.hasApplied(student, company)) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "You have already applied for this company!"
            );

            return "redirect:/applications";
        }

        Application application = new Application();
        application.setStudent(student);
        application.setCompany(company);

        applicationService.apply(application);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Application submitted successfully!"
        );

        return "redirect:/applications";
    }

    @GetMapping("/applications")
    public String applications(HttpSession session,
                               Model model) {

        Student student =
                (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";
        }

        model.addAttribute("applications",
                applicationService.getApplicationsByStudent(student));

        return "applications";
    }
}