package com.backend.springboot.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.backend.springboot.model.MenuMealPrice;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.MealPrice;

public interface MenuMealPriceRepository extends JpaRepository<MenuMealPrice, Integer>{
	@Query("select distinct mp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.menu.id = ?1 and mmp.mealPrice.deleted = false")
	Set<MealPrice>findAllMealPricesByMenuId(Integer id);
	
	@Query("select distinct mp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.menu.id = ?1 and mmp.mealPrice.deleted = false")
	List<MealPrice>findAllMealsPricesByMenuId(Integer id);
	
	@Query("select mmp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.mealPrice.id = ?1 and mmp.menu.id = ?2")
	Optional<MenuMealPrice> findMenuMealPriceByMealPriceIdAndMenuId(Integer mealPriceId, Integer menuId);
	
	
	@Query("select distinct mp from MenuMealPrice mmp join mmp.mealPrice mp join mmp.menu m where mmp.menu.id = ?1 and mmp.mealPrice.deleted = false and mmp.mealPrice.meal.type = ?2")
	List<MealPrice>findAllMealsPricesByMenuIdAndMealType(Integer id, MealType type);

}
