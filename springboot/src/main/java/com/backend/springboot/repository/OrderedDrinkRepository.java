package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.OrderedDrink;

public interface OrderedDrinkRepository extends JpaRepository<OrderedDrink, Integer>{

}
