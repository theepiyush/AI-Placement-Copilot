package com.piyush.placementcopilot.repository;

import com.piyush.placementcopilot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

}