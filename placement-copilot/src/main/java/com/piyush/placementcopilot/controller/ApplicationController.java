package com.piyush.placementcopilot.controller;

import com.piyush.placementcopilot.entity.Application;
import com.piyush.placementcopilot.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply to Company
    @PostMapping("/apply")
    public Application apply(@RequestBody Application application) {
        return applicationService.apply(application);
    }

    // Get All Applications
    @GetMapping("/all")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }
}