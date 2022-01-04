package com.backend.springboot.constants;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;

public class MealConstants {
	//data for meal repository tests
	public static MealType MEALTYPE = MealType.MAIN_COURSE;
	public static int LIST_SIZE_MAIN_COURSE = 2;
	public static String NAME_OF_FIRST_MAIN_COURSE = "Pasulj";
	
	public static Integer NON_EXISTING_MEAL_ID = 100;
	public static String NON_EXISTING_MEAL_NAME = "Ime";
	public static String NON_EXISTING_MEAL_DESCRIPTION = "Opis";
	public static Meal NON_EXISTING_MEAL = Meal.builder().id(NON_EXISTING_MEAL_ID).name(NON_EXISTING_MEAL_NAME).description(NON_EXISTING_MEAL_DESCRIPTION).deleted(false).type(MealType.APPENDICES).build();
	
	public static String EXISTING_NAME = "Pecenje";
	public static String EXISTING_DESCRIPTION = "Jako";
	public static Integer CHECK_ID = 4;
	
	public static Integer EXISTING_MEAL_ID = 2;
	public static String EXISTING_MEAL_NAME = "Vocna salata";
	public static String EXISTING_MEAL_DESCRIPTION = "Osvjezavajuce";
	public static Meal EXISTING_MEAL = Meal.builder().id(EXISTING_MEAL_ID).name(EXISTING_MEAL_NAME).description(EXISTING_MEAL_DESCRIPTION).
			amountNumber(500).amountUnit("g").deleted(false).image("slika").mealDifficulty(MealDifficulty.EASY).timePreparation(2).
			type(MEALTYPE).build();
	public static Meal CHANGE_EXISTING_MEAL = Meal.builder().id(EXISTING_MEAL_ID).name("Drugo ime").description("Drugi opis").
			amountNumber(600).amountUnit("kg").deleted(true).image("Druga slika").mealDifficulty(MealDifficulty.HARD).timePreparation(3).
			type(MealType.SALAD).build();
	public static Meal CHANGE_EXISTING_MEAL2 = Meal.builder().id(NON_EXISTING_MEAL_ID).name("Drugo ime").description("Drugi opis").
			amountNumber(600).amountUnit("kg").deleted(true).image("Druga slika").mealDifficulty(MealDifficulty.HARD).timePreparation(3).
			type(MealType.SALAD).build();
	
	
	
	//integration tests
	public static String INAME = "Kajgana";
	public static String IDESCRIPTION = "Domace, zdravo";
	public static Meal IMEAL = Meal.builder().id(10).name(INAME).description(IDESCRIPTION).build();
	
	
	
}
