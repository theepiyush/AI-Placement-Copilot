package com.piyush.placementcopilot.service;

import com.piyush.placementcopilot.entity.Application;
import com.piyush.placementcopilot.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Apply for Company
    public Application apply(Application application) {
        return applicationRepository.save(application);
    }

    // Get All Applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}