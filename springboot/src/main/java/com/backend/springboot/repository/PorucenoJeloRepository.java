package com.backend.springboot.repository;

import com.backend.springboot.model.PorucenoJelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorucenoJeloRepository extends JpaRepository<PorucenoJelo, Integer> {
}
