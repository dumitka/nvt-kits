package com.backend.springboot.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.backend.springboot.model.MenuMealPrice;

import com.backend.springboot.model.MealPrice;

public interface MenuMealPriceRepository extends JpaRepository<MenuMealPrice, Integer>{
	@Query("select distinct mp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.menu.id = ?1")
	Set<MealPrice>findAllMealPricesByMenuId(Integer id);
	
	@Query("select mmp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.mealPrice.id = ?1 and mmp.menu.id = ?2 ")
	Optional<MenuMealPrice> findMenuMealPriceByMealPriceIdAndMenuId(Integer mealPriceId, Integer menuId);
	

}
