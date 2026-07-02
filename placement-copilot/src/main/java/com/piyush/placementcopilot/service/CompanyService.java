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

    // Get Company By Id
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Delete Company
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    // Eligible Companies
    public List<Company> getEligibleCompanies(Double cgpa) {
        return companyRepository.findByMinimumCgpaLessThanEqual(cgpa);
    }
    public void closeCompany(Long id) {

        Company company = companyRepository.findById(id).orElse(null);

        if (company != null) {
            company.setActive(false);
            companyRepository.save(company);
        }
    }
}