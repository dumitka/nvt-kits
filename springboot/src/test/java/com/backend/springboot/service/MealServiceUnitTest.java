package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.backend.springboot.enums.MealDifficulty;
import com.backend.springboot.enums.MealType;
import com.backend.springboot.model.Meal;
import com.backend.springboot.repository.MealRepository;
import static com.backend.springboot.constants.MealConstants.ID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class MealServiceUnitTest {
	
	@Autowired 
	MealService mealService;
	
	@MockBean
	MealRepository mealRepository;
	
	
	@Test
	public void TestGetAllMealbyMealType() {
		List<Meal>mainCourses = new ArrayList<Meal>();
		mainCourses.add(new Meal("Pasulj", MealType.MAIN_COURSE, "Jako dobro", MealDifficulty.HARD, 180, 330, "g", "nema", false)); 
		mainCourses.add(new Meal("Pecenje", MealType.MAIN_COURSE, "Jos bolje", MealDifficulty.HARD, 300, 1, "kg", "nema", false));

		given(mealRepository.findMealbyMealType(MealType.MAIN_COURSE)).willReturn(mainCourses);
		assertEquals(2, mainCourses.size());
	}
	
	
	@Test
	public void TestChangeMeal() {
		//param
		Meal meal = new Meal("Pecenje la lijepo", MealType.HOT_APPETIZER, "Lijepo", MealDifficulty.MEDIUM, 320, 800, "g", "neka", true);
		
		given(mealRepository.findMealById(ID)).willReturn(new Meal("Pecenje", MealType.MAIN_COURSE, "Jos bolje", MealDifficulty.HARD, 300, 1, "kg", "nema", false));
		
		Meal current = mealRepository.findMealById(1);
		current.setDescription(meal.getDescription());
		current.setAmountNumber(meal.getAmountNumber());
		current.setAmountUnit(meal.getAmountUnit());
		current.setImage(meal.getImage());
		current.setMealDifficulty(meal.getMealDifficulty());
		current.setName(meal.getName());
		current.setTimePreparation(meal.getTimePreparation());
		current.setType(meal.getType());
		
		Meal savedMeal = current;
		savedMeal.setId(ID);
		
		//provjera
		assertEquals(ID, savedMeal.getId());
		assertEquals("Lijepo", savedMeal.getDescription());
		assertEquals(Integer.valueOf(800), savedMeal.getAmountNumber());
		assertEquals("g", savedMeal.getAmountUnit());
		assertEquals("neka", savedMeal.getImage());
		assertEquals(MealDifficulty.MEDIUM, savedMeal.getMealDifficulty());
		assertEquals("Pecenje la lijepo", savedMeal.getName());
		assertEquals(Integer.valueOf(320), savedMeal.getTimePreparation());
		assertEquals(MealType.HOT_APPETIZER, savedMeal.getType());
		
	}
	
	
	
	@Test
	public void TestDeleteMeal() {
		//param
		Meal meal = new Meal("Pecenje", MealType.MAIN_COURSE, "Jos bolje", MealDifficulty.HARD, 300, 1, "kg", "nema", false);
		meal.setId(ID);
		given(mealRepository.findMealById(ID)).willReturn(meal);
		
		Meal current = mealRepository.findMealById(meal.getId());
		current.setDeleted(true);
		
		Meal savedMeal = current;
		savedMeal.setId(ID);
		
		//provjera
		assertEquals(true, savedMeal.getDeleted());
		
	}
	
}
