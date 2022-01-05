package com.backend.springboot.constants;

import static org.mockito.ArgumentMatchers.floatThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.model.MealPrice;
import com.backend.springboot.model.Menu;
import com.backend.springboot.model.MenuMealPrice;


public class MealPriceConstants {
	//data for meal repository tests and service
		public static MealType MEALTYPE = MealType.MAIN_COURSE;
		public static int LIST_SIZE_MAIN_COURSE = 2;
		public static String NAME_OF_FIRST_MAIN_COURSE = "Pasulj";
		
		public static Integer EXISTING_MEAL_PRICE_ID = 2;
		public static String EXISTING_MEAL_NAME = "Vocna salata";
		
		
		public static Integer EXISTING_MEAL_ID = 2;
		public static String EXISTING_MEAL_DESCRIPTION = "Osvjezavajuce";
		public static Meal EXISTING_MEAL = Meal.builder().id(EXISTING_MEAL_ID).name(EXISTING_MEAL_NAME).description(EXISTING_MEAL_DESCRIPTION).
				amountNumber(500).amountUnit("g").deleted(false).image("slika").mealDifficulty(MealDifficulty.EASY).timePreparation(2).
				type(MEALTYPE).build();
		public static float PRICE = 500;
		public static MealPrice EXISTING_MEALPRICE = MealPrice.builder().id(EXISTING_MEAL_PRICE_ID).meal(EXISTING_MEAL).priceAmount(PRICE).deleted(false).build();
		
		public static Integer NEW_MEALPRICE_ID = 3;
		public static MealPrice NEW_MEALPRICE = MealPrice.builder().id(NEW_MEALPRICE_ID).meal(EXISTING_MEAL).priceAmount(PRICE).deleted(false).build();
		
		public static Integer CHANGED_MEALPRICE_ID = EXISTING_MEAL_PRICE_ID;
		public static float CHANGED_PRICE = 500;
		public static MealPrice CHANGED_MEALPRICE = MealPrice.builder().id(CHANGED_MEALPRICE_ID).meal(EXISTING_MEAL).priceAmount(CHANGED_PRICE).deleted(false).build();
		
		
		public static Integer NON_EXISTING_MEAL_PRICE_ID = 100;
		public static float PRICE_NXMP = 400;
		public static MealPrice NON_EXISTING_MEALPRICE = MealPrice.builder().id(NON_EXISTING_MEAL_PRICE_ID).meal(EXISTING_MEAL).priceAmount(PRICE_NXMP).deleted(false).menuMealPrices(null).build();
		
		
		public static Integer MENU_ID = 2;
		public static Menu CURRENT_MENU = Menu.builder().id(MENU_ID).dateOfValidation(LocalDateTime.now()).build();
		
		public static Integer MENU_MEALPRICE_ID = 1;
		public static MenuMealPrice MENU_MEALPRICE = MenuMealPrice.builder().id(MENU_MEALPRICE_ID).mealPrice(EXISTING_MEALPRICE).menu(CURRENT_MENU).build();


		public static MealPrice LIST_ELEMENT1 = MealPrice.builder().id(1).meal(EXISTING_MEAL).priceAmount(CHANGED_PRICE).deleted(false).build();
		public static MealPrice LIST_ELEMENT2 = MealPrice.builder().id(2).meal(EXISTING_MEAL).priceAmount(CHANGED_PRICE).deleted(false).build();
		public static MealPrice LIST_ELEMENT3 = MealPrice.builder().id(3).meal(EXISTING_MEAL).priceAmount(CHANGED_PRICE).deleted(false).build();
		public static MealPrice LIST_ELEMENT4 = MealPrice.builder().id(4).meal(EXISTING_MEAL).priceAmount(CHANGED_PRICE).deleted(false).build();
		
		
		public static List<MealPrice> ALL_MEAL_PRICES = Arrays.asList(LIST_ELEMENT1, LIST_ELEMENT2, LIST_ELEMENT3, LIST_ELEMENT4);
		public static List<MealPrice> MEALS_FROM_CURRENT_MENU = Arrays.asList(LIST_ELEMENT1, LIST_ELEMENT3);
		



		//integration 
		public static Integer IEXISTING_MEAL_PRICE_ID = 1;
		public static Integer IEXISTING_MEAL_ID = 2;
		public static String IEXISTING_MEAL_DESCRIPTION = "Osvjezavajuce";
		public static Meal IEXISTING_MEAL = Meal.builder().id(IEXISTING_MEAL_ID).name(EXISTING_MEAL_NAME).description(IEXISTING_MEAL_DESCRIPTION).
				amountNumber(500).amountUnit("g").deleted(false).image("slika").mealDifficulty(MealDifficulty.EASY).timePreparation(2).
				type(MEALTYPE).build();
		public static float IPRICE = 500;
		public static MealPrice IEXISTING_MEALPRICE = MealPrice.builder().id(IEXISTING_MEAL_PRICE_ID).meal(IEXISTING_MEAL).priceAmount(IPRICE).deleted(false).build();
		
		public static Meal MEAL_FROM_DATABASE = Meal.builder().id(5).deleted(false).name("Supa").description("Toplo").type(MealType.HOT_APPETIZER)
				.mealDifficulty(MealDifficulty.MEDIUM).timePreparation(30).amountNumber(200).amountUnit("ml").image("nema").build();
		public static MealPrice INEW_MEALPRICE = MealPrice.builder().meal(MEAL_FROM_DATABASE).priceAmount(IPRICE-200).deleted(false).build();
		
		public static MealPrice DELETE_MEAL_PRICE = MealPrice.builder().id(3).deleted(false).build();
	
		
		
		


}
