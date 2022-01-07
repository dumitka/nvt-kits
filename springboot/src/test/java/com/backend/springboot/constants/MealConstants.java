package com.backend.springboot.constants;

import java.util.List;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;

public class MealConstants {
	/*
	 	insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Kajgana', 1, 'Domace, zdravo', 0, 5, 200, 'g', 'nema', false);
		insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Vocna salata', 3, 'Osvjezavajuce', 0, 10, 300, 'g', 'nema', true);
		insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pasulj', 2, 'Jako', 2, 180, 330, 'g', 'nema', false);
		insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Pecenje', 2, 'Jako', 2, 300, 1, 'kg', 'nema', false);
		insert into meals(name, type, description, meal_difficulty, time_preparation, amount_number, amount_unit, image, deleted) values ('Supa', 1, 'Toplo', 1, 30, 200, 'ml', 'nema', false);
	 */
	
	/* 
	 * NUMBER --> X
	 * REPOSITORY TESTS --> RX
	 * SERVICE TESTS --> SX
	 * CONTROLLER TESTS --> CX
	 */
	
	//R1 //S7
	public static MealType MEALTYPE_MAIN_COURSE = MealType.MAIN_COURSE;
	public static int MAIN_COURSE_LIST_IN_DATABASE = 2;
	public static String NAME_OF_FIRST_MAIN_COURSE = "Pasulj";
	
	//R2
	public static MealType MEALTYPE_SALAD = MealType.SALAD;
	public static int SALAD_LIST_IN_DATABASE = 0;
	
	//R3 //R5 //S1 //S3
	public static Integer EXISTING_MEAL_ID = 1; //kajgana
	public static String EXISTING_MEAL_NAME = "Kajgana"; 
	
	//R5 //S3
	public static String EXISTING_MEAL_DESCRIPTION = "Domace, zdravo";
	
	//R4 //S9
	public static Integer NON_EXISTING_MEAL_ID = 100;
	
	//S3
	public static String NON_EXISTING_MEAL_NAME = "Nepostojece jelo";
	public static String NON_EXISTING_MEAL_DESCRIPTION = "Nepostojece jelo";
	

	
	//S1
	public static Meal EXISTING_MEAL = Meal.builder().id(EXISTING_MEAL_ID).name(EXISTING_MEAL_NAME).description(EXISTING_MEAL_DESCRIPTION)
			.amountNumber(200).amountUnit("g").type(MealType.HOT_APPETIZER).deleted(false).image("nema")
			.mealDifficulty(MealDifficulty.EASY).timePreparation(5).build(); //kajgana from database
	
	
	//S7
	public static Meal MAIN_COURSE1 = Meal.builder().id(3).name("Pasulj").description("Jako")
			.amountNumber(330).amountUnit("g").type(MealType.MAIN_COURSE).deleted(false).image("nema")
			.mealDifficulty(MealDifficulty.HARD).timePreparation(180).build(); //pasulj from database
	
	public static Meal MAIN_COURSE2 = Meal.builder().id(4).name("Pecenje").description("Jako")
			.amountNumber(1).amountUnit("kg").type(MealType.MAIN_COURSE).deleted(false).image("nema")
			.mealDifficulty(MealDifficulty.HARD).timePreparation(300).build(); //pecenje from database
	
	public static List<Meal> LIST_OF_MAIN_COURSE = List.of(MAIN_COURSE1, MAIN_COURSE2);
	public static int LIST_OF_MAIN_COURSE_SIZE = 2;
	
	
	//S8
	public static Meal NEW_MEAL = Meal.builder().name("Novo jelo").description("Novi opis")
			.amountNumber(100).amountUnit("g").type(MealType.APPENDICES).deleted(false).image("nema")
			.mealDifficulty(MealDifficulty.EASY).timePreparation(10).build(); //does not exists in database
	
	
	//S9
	public static Meal NON_EXISTING_MEAL = Meal.builder().id(NON_EXISTING_MEAL_ID).name(NON_EXISTING_MEAL_NAME).description(NON_EXISTING_MEAL_DESCRIPTION)
			.type(MealType.HOT_APPETIZER).deleted(false)
			.build(); //non existing name in database
	
	
	//S10
	public static String CHANGED_DESCRIPTION = "Novi opis";
	public static int CHANGED_AMOUNT_NUMBER = 300;
	public static String CHANGED_AMOUNT_UNIT = "kg";
	public static MealType CHANGED_TYPE = MealType.COLD_APPETIZER;
	public static String CHANGED_IMAGE = "promjenjena slika";
	public static MealDifficulty CHANGED_MEAL_DIFFICULTY = MealDifficulty.HARD;
	public static int CHANGED_TIME_PREPARATION = 5;
	public static Meal CHANGED_MEAL = Meal.builder().id(EXISTING_MEAL_ID).name(EXISTING_MEAL_NAME).description(CHANGED_DESCRIPTION)
			.amountNumber(CHANGED_AMOUNT_NUMBER).amountUnit(CHANGED_AMOUNT_UNIT).type(CHANGED_TYPE).deleted(false).image(CHANGED_IMAGE)
			.mealDifficulty(CHANGED_MEAL_DIFFICULTY).timePreparation(CHANGED_TIME_PREPARATION).build(); //kajgana from database
	
	
	
	//S11
	public static Integer DELETED_MEAL_ID = 2;
	public static Meal DELETED_MEAL = Meal.builder().id(DELETED_MEAL_ID).name("Vocna salata").description("Osvjezavajuce")
			.amountNumber(300).amountUnit("g").type(MealType.DESERT).deleted(true).image("nema")
			.mealDifficulty(MealDifficulty.EASY).timePreparation(10).build(); //vocna salata from database
	
}
