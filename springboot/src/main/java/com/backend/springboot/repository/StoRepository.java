package com.backend.springboot.repository;

import com.backend.springboot.model.Sto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoRepository extends JpaRepository<Sto, Integer> {

    List<Sto> findAll();
}
