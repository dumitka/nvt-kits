package com.backend.springboot.repository;

import com.backend.springboot.model.CenaPica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenaPicaRepository extends JpaRepository<CenaPica, Integer> {
}
