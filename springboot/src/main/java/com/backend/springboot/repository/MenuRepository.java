package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
