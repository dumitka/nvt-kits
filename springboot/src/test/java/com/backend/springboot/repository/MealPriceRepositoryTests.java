package com.backend.springboot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.backend.springboot.model.MealPrice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import static com.backend.springboot.constants.MealConstants.MEALTYPE_MAIN_COURSE;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST;
import static com.backend.springboot.constants.MealPriceConstants.MAIN_COURSE_MEAL_PRICE_LIST_SIZE;
import static com.backend.springboot.constants.MealPriceConstants.EXISTING_MEAL_PRICE;
import static com.backend.springboot.constants.MealPriceConstants.NON_EXISTING_MEAL_PRICE;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MealPriceRepositoryTests {
	@Autowired
	MealPriceRepository mealPriceRepository;
	
	
	
	@Test
	public void findAllMealPricebyMealType_givenExistingMealPrice_ListofMealPrice() {
		List<MealPrice> foundList = mealPriceRepository.findAllMealPricebyMealType(MEALTYPE_MAIN_COURSE);
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST_SIZE, foundList.size());
		assertEquals(MEALTYPE_MAIN_COURSE, foundList.get(0).getMeal().getType());
		assertEquals(MAIN_COURSE_MEAL_PRICE_LIST.get(0).getMeal().getName(), foundList.get(0).getMeal().getName());
	}
	
	
	@Test
	public void findMealPriceById_givenExistingId_MealPrice() {
		Optional<MealPrice> found = mealPriceRepository.findMealPriceById(EXISTING_MEAL_PRICE.getId());
		assertTrue(found.isPresent());
		assertEquals(EXISTING_MEAL_PRICE.getId(), found.get().getId());
		assertEquals(EXISTING_MEAL_PRICE.getMeal().getName(), found.get().getMeal().getName());
	}
	
	
	@Test
	public void findMealPriceById_givenNonExistingId_Null() {
		Optional<MealPrice> found = mealPriceRepository.findMealPriceById(NON_EXISTING_MEAL_PRICE.getId());
		assertFalse(found.isPresent());
	}
	
}
