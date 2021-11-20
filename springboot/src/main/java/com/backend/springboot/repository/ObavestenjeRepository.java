package com.backend.springboot.repository;

import com.backend.springboot.model.Obavestenje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Integer> {
}
