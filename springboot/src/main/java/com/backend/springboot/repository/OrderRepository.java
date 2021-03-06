package com.backend.springboot.repository;

import com.backend.springboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	public Order findOneByIdAndIsDeletedFalse(Integer id);
	
	public Order findOneByDeskIdAndIsDeletedFalse(Integer deskId);
}
