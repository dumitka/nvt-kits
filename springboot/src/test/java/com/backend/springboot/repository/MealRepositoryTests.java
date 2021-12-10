package com.backend.springboot.repository;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.springboot.model.Meal;
import static com.backend.springboot.constants.MealConstants.MEAL_TYPE;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class MealRepositoryTests {
	@Autowired
    private MealRepository mealRepository;

    @Test
    public void TestFindMealbyMealType(){
    	List<Meal> foundMeals = mealRepository.findMealbyMealType(MEAL_TYPE);
    	assertEquals(2, foundMeals.size());
    }
    
    
    @Test
    public void TestFindMealById(){
    	Meal found = mealRepository.findMealById(2);
    	assertEquals("Vocna salata", found.getName());
    }
}
