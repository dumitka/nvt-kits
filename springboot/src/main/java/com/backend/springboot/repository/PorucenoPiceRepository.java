package com.backend.springboot.repository;

import com.backend.springboot.model.PorucenoPice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorucenoPiceRepository extends JpaRepository<PorucenoPice, Integer> {
}
