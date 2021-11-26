package com.backend.springboot.repository;

import com.backend.springboot.enums.OrderedItemStatus;
import com.backend.springboot.model.OrderedDrink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedDrinkRepository extends JpaRepository<OrderedDrink, Integer>{
	OrderedDrink findOneById(Integer id);

	List<OrderedDrink> findByStatus(OrderedItemStatus status);
}
