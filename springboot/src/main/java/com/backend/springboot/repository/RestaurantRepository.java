package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

}
