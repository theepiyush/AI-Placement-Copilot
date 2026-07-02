package com.piyush.placementcopilot.repository;

import com.piyush.placementcopilot.entity.Application;
import com.piyush.placementcopilot.entity.Company;
import com.piyush.placementcopilot.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudent(Student student);

    boolean existsByStudentAndCompany(Student student, Company company);

    @Transactional
    @Modifying
    @Query("DELETE FROM Application a WHERE a.company = :company")
    void deleteApplicationsByCompany(Company company);

}