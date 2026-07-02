package com.piyush.placementcopilot.service;

import com.piyush.placementcopilot.entity.Application;
import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.entity.Student;
import com.piyush.placementcopilot.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Apply
    public Application apply(Application application) {
        return applicationRepository.save(application);
    }

    // All Applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Student Applications
    public List<Application> getApplicationsByStudent(Student student) {
        return applicationRepository.findByStudent(student);
    }

    // Duplicate Check
    public boolean hasApplied(Student student, Company company) {
        return applicationRepository.existsByStudentAndCompany(student, company);
    }

    // Delete Applications of Company
    public void deleteApplicationsByCompany(Company company) {
        applicationRepository.deleteApplicationsByCompany(company);
    }

}