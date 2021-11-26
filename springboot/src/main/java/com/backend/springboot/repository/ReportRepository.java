package com.backend.springboot.repository;

import com.backend.springboot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findAll();

    Report save(Report report);

}
