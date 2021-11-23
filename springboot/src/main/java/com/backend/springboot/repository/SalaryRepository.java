package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
