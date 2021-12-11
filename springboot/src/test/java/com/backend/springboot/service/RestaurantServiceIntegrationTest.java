package com.backend.springboot.service;

import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.backend.springboot.constants.RestaurantConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class RestaurantServiceIntegrationTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void findAllTest() {
        List<Restaurant> pronadjena = this.restaurantService.findAll();
        assertEquals(ONE_RESTAURANT, pronadjena.size());
    }

    @Test
    public void findOneTest() {
        Restaurant pronadjena = this.restaurantService.findOne(RESTAURANT_ID);
        int id = pronadjena.getId();
        assertEquals(RESTAURANT_ID, id);
    }

    @Test
    public void saveTest() {
        Restaurant novi = new Restaurant();
        Restaurant pronadjena = this.restaurantService.save(novi);
        int id = pronadjena.getId();
        assertEquals(NEW_RESTAURANT_ID, id);
        this.restaurantRepository.delete(pronadjena);
    }
}
