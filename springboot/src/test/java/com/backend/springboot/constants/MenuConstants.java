package com.backend.springboot.constants;

import java.time.LocalDateTime;
import java.util.Set;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;

public class MenuConstants {
	public static Integer ID_OF_PAST_MENU = 1;
	public static Integer ID_OF_CURRENT_MENU = 2;
	public static Integer ID_OF_FUTURE_MENU = 3;
	
	public static LocalDateTime EXISTING_DATE_MENU = LocalDateTime.of(2020, 10, 1, 0, 0);
	public static LocalDateTime NON_EXISTING_DATE_MENU = LocalDateTime.of(2023, 10, 1, 0, 0);
	
	public static Menu CURRENT_MENU = Menu.builder().id(ID_OF_CURRENT_MENU).current(true).dateOfValidation(EXISTING_DATE_MENU).build();
	
	
	
	
	public static float STARTING_PRICE = 0;
	public static Meal MEAL1 = Meal.builder().id(1).name("Hladno predjelo 1").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 1 opis").mealDifficulty(MealDifficulty.EASY).timePreparation(5).amountNumber(500).amountUnit("g").image("http//www.image1.jpg").build();
    public static Meal MEAL2 = Meal.builder().id(2).name("Hladno predjelo 2").deleted(false).type(MealType.COLD_APPETIZER).description("Hladno predjelo 2 opis").mealDifficulty(MealDifficulty.MEDIUM).timePreparation(10).amountNumber(300).amountUnit("g").image("http//www.image2.jpg").build();
    public static MealPrice ELEMENT1 = MealPrice.builder().id(1).meal(MEAL1).price(STARTING_PRICE + 200).build();
    public static MealPrice ELEMENT2 = MealPrice.builder().id(2).meal(MEAL2).price(STARTING_PRICE + 300).build();
    
	
	
	public static MenuMealPrice MMP1 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT1).deleted(false).menu(CURRENT_MENU).build();
    public static MenuMealPrice MMP2 = MenuMealPrice.builder().id(1).mealPrice(ELEMENT2).deleted(false).menu(CURRENT_MENU).build();
    public static Set<MenuMealPrice> MEALS_FOR_CURRENT_MENU = Set.of(MMP1, MMP2);
}
