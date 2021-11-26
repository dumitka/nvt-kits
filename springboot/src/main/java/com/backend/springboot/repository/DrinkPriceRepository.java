package com.backend.springboot.repository;

import com.backend.springboot.model.DrinkPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkPriceRepository extends JpaRepository<DrinkPrice, Integer> {

    DrinkPrice findOneByDrinkId(Integer drinkId);

}
