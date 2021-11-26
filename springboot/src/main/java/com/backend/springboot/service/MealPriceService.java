package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.repository.MealPriceRepository;


@Component
@Primary
@Service
public class MealPriceService {
	@Autowired
	private MealPriceRepository repository;
	
	public List<MealPrice> getAllMealPricebyMealType(MealType type){
		return repository.findAllMealPricebyMealType(type);
	}
}
