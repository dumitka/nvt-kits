package com.backend.springboot.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.model.MealPrice;
import static com.backend.springboot.constants.MealPriceConstants.MEAL_TYPE;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MealPriceRepositoryTest {
	
	@Autowired 
	MealPriceRepository mealPriceRepository;
	
	@Test 
	public void TestFindAllMealPricebyMealType() {
		List<MealPrice> founded = mealPriceRepository.findAllMealPricebyMealType(MEAL_TYPE);
		assertEquals(2, founded.size());
	}
}
