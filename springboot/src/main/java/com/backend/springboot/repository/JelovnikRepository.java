package com.backend.springboot.repository;

import com.backend.springboot.model.Jelovnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JelovnikRepository extends JpaRepository<Jelovnik, Integer> {
}
