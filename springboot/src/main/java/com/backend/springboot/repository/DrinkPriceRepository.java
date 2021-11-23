package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.DrinkPrice;

public interface DrinkPriceRepository extends JpaRepository<DrinkPrice, Integer>{

}
