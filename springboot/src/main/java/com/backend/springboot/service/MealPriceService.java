package com.backend.springboot.service;

import com.backend.springboot.model.MealPrice;
import com.backend.springboot.repository.MealPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MealPriceService {

    @Autowired
    private MealPriceRepository mealPriceRepository;

    public MealPrice findPriceOfMealForDate(LocalDateTime date, Integer mealId) { //todo date instead of datetime?
        return mealPriceRepository.findOneByMealId(mealId);//
    }
}
