package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Add Company
    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    // Get All Companies
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}