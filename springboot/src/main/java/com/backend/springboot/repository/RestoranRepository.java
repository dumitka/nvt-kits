package com.backend.springboot.repository;

import com.backend.springboot.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestoranRepository extends JpaRepository<Restoran, Integer> {
}
