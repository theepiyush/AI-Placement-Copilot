package com.piyush.placementcopilot.repository;

import com.piyush.placementcopilot.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}