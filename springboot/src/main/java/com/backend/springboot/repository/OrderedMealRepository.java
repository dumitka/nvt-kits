package com.backend.springboot.repository;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedMealRepository extends JpaRepository<OrderedMeal, Integer>{
	OrderedMeal findOneById(Integer id);

	List<OrderedMeal> findByStatus(OrderedItemStatus status);
	
	List<OrderedMeal> findByCookId(Integer id);
}
