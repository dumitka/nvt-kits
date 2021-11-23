package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Desk;

public interface DeskRepository extends JpaRepository<Desk, Integer>{

}
