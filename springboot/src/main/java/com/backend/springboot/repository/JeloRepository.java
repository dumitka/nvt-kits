package com.backend.springboot.repository;

import com.backend.springboot.model.Jelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeloRepository extends JpaRepository<Jelo, Integer> {
}
