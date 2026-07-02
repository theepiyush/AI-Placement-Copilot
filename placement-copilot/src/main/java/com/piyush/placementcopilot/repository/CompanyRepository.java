package com.piyush.placementcopilot.repository;

import com.piyush.placementcopilot.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findById(long id);

    List<Company> findByMinimumCgpaLessThanEqual(Double cgpa);

}