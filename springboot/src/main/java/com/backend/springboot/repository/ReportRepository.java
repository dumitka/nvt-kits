package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

}
