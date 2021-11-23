package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
