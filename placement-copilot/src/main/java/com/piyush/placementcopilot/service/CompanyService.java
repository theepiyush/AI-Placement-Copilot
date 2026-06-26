package com.piyush.placementcopilot.service;

import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Add Company
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    // Get All Companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}