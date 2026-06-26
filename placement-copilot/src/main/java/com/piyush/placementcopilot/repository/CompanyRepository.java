package com.piyush.placementcopilot.repository;

import com.piyush.placementcopilot.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}