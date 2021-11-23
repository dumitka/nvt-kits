package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.OrderedMeal;

public interface OrderedMealRepository extends JpaRepository<OrderedMeal, Integer>{

}
