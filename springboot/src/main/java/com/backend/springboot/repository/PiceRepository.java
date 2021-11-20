package com.backend.springboot.repository;

import com.backend.springboot.model.Pice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PiceRepository extends JpaRepository<Pice, Integer> {
}
