package com.backend.springboot.repository;

import com.backend.springboot.model.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {
}
