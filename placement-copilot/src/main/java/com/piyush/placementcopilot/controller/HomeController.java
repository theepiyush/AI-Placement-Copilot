package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("companies", companyService.getAllCompanies());

        return "home";
    }
}