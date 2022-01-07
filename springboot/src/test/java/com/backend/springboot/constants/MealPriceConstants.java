package com.backend.springboot.constants;



import static com.backend.springboot.constants.MealConstants.EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.NON_EXISTING_MEAL;
import static com.backend.springboot.constants.MealConstants.MAIN_COURSE1;
import static com.backend.springboot.constants.MealConstants.MAIN_COURSE2;
import static com.backend.springboot.constants.MenuConstants.CURRENT_MENU;

import java.util.List;

import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;

import com.backend.springboot.model.MenuMealPrice;


public class MealPriceConstants {
	//insert into meal_prices(meal_id, price_amount, deleted) values (1, 300, false);
	//insert into meal_prices(meal_id, price_amount, deleted) values (2, 330, false);
	//insert into meal_prices(meal_id, price_amount, deleted) values (3, 480, false);
	//insert into meal_prices(meal_id, price_amount, deleted) values (4, 700, false);
		
	public static float STARTING_PRICE = 0;

	//kajgana
	public static MealPrice EXISTING_MEAL_PRICE = MealPrice.builder().id(1).meal(EXISTING_MEAL).deleted(false).priceAmount(STARTING_PRICE + 300).build();
	public static MenuMealPrice EXISTING_MENU_MEAL_PRICE = MenuMealPrice.builder().mealPrice(EXISTING_MEAL_PRICE).menu(CURRENT_MENU).deleted(false).build();
	
	
	public static MealPrice NON_EXISTING_MEAL_PRICE = MealPrice.builder().id(100).meal(NON_EXISTING_MEAL).deleted(false).priceAmount(STARTING_PRICE + 100).build();
	

	public static MealPrice MAIN_COURSE1_MEAL_PRICE = MealPrice.builder().id(3).meal(MAIN_COURSE1).deleted(false).priceAmount(STARTING_PRICE + 480).build();
	public static MealPrice MAIN_COURSE2_MEAL_PRICE = MealPrice.builder().id(4).meal(MAIN_COURSE2).deleted(false).priceAmount(STARTING_PRICE + 700).build();
	public static List<MealPrice> MAIN_COURSE_MEAL_PRICE_LIST = List.of(MAIN_COURSE1_MEAL_PRICE, MAIN_COURSE2_MEAL_PRICE);
	public static int MAIN_COURSE_MEAL_PRICE_LIST_SIZE = 2;
	
	
	public static MealPrice NEW_MEAL_PRICE = MealPrice.builder().meal(EXISTING_MEAL).priceAmount(STARTING_PRICE + 500).deleted(false).build();
	public static MenuMealPrice NEW_MENU_MEAL_PRICE = MenuMealPrice.builder().mealPrice(NEW_MEAL_PRICE).menu(CURRENT_MENU).deleted(false).build();
	
	
	public static MealPrice CHANGED_MEAL_PRICE = MealPrice.builder().id(1).meal(EXISTING_MEAL).priceAmount(STARTING_PRICE + 1000).build();
	
	//for list
	public static Meal MEAL1 = Meal.builder().id(1).name("Kajgana").deleted(false).type(MealType.HOT_APPETIZER).build();
	public static Meal MEAL2 = Meal.builder().id(2).name("Vocna salata").deleted(true).type(MealType.DESERT).build();
	public static Meal MEAL3 = Meal.builder().id(3).name("Pasulj").deleted(false).type(MealType.MAIN_COURSE).build();
	public static Meal MEAL4 = Meal.builder().id(4).name("Pecenje").deleted(false).type(MealType.MAIN_COURSE).build();
	public static Meal MEAL5 = Meal.builder().id(5).name("Supa").deleted(false).type(MealType.HOT_APPETIZER).build();
	
	public static List<Meal> LIST_OF_ALL_MEALS = List.of(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5);
	
	
	public static MealPrice MEAL_PRICE1 = MealPrice.builder().id(1).meal(MEAL1).deleted(false).priceAmount(STARTING_PRICE + 300).build();
	public static MealPrice MEAL_PRICE3 = MealPrice.builder().id(3).meal(MEAL3).deleted(false).priceAmount(STARTING_PRICE + 480).build();
	public static MealPrice MEAL_PRICE4 = MealPrice.builder().id(4).meal(MEAL4).deleted(false).priceAmount(STARTING_PRICE + 700).build();
	
	public static List<MealPrice> LIST_OF_MEAL_PRICES_IN_CURRENT_MENU = List.of(MEAL_PRICE1, MEAL_PRICE3, MEAL_PRICE4);
	public static List<Meal> LIST_OF_MEALS_THAT_IS_NOT_IN_CURRENT_MENU = List.of(MEAL5);
	
	
}
