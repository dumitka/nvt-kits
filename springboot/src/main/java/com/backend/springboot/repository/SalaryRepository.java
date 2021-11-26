package com.backend.springboot.repository;

import com.backend.springboot.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    Salary save(Salary salary);

    List<Salary> findAll();

    List<Salary> findByUserId(Integer userId);

}
