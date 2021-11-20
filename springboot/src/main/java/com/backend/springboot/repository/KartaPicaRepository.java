package com.backend.springboot.repository;

import com.backend.springboot.model.KartaPica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KartaPicaRepository extends JpaRepository<KartaPica, Integer> {
}
