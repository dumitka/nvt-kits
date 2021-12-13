package com.backend.springboot.service;

import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.backend.springboot.constants.RestaurantConstants.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantServiceUnitTest {

    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    private Restaurant nesacuvanRestoran;

    @Before
    public void setUpp() {
        Restaurant restoran =  Restaurant.builder().id(RESTAURANT_ID).build();
        List<Restaurant> sviRestorani = new ArrayList<>();
        sviRestorani.add(restoran);

        nesacuvanRestoran =  new Restaurant();

        given(this.restaurantRepository.findAll()).willReturn(sviRestorani);
        given(this.restaurantRepository.findById(RESTAURANT_ID)).willReturn(java.util.Optional.of(restoran));
        given(this.restaurantRepository.save(this.nesacuvanRestoran)).willReturn(restoran);
    }

    @Test
    public void findAll_EverythingOk_ReturnListRestaurant() {
        List<Restaurant> pronadjena = this.restaurantService.findAll();
        verify(this.restaurantRepository).findAll();
        assertEquals(ONE_RESTAURANT, pronadjena.size());
    }

    @Test
    public void findOne_EverythingOk_ReturnRestaurant() {
        Restaurant pronadjena = this.restaurantService.findOne(RESTAURANT_ID);
        verify(this.restaurantRepository).findById(RESTAURANT_ID);
        int id = pronadjena.getId();
        assertEquals(RESTAURANT_ID, id);
    }

    @Test
    public void findOne_NotExistId_ReturnNull() {
        Restaurant pronadjena = this.restaurantService.findOne(NOT_RESTAURANT_ID);
        verify(this.restaurantRepository).findById(NOT_RESTAURANT_ID);
        assertNull(pronadjena);
    }

    @Test
    public void save_EverythingOk_ReturnRestaurant() {
        Restaurant pronadjena = this.restaurantService.save(this.nesacuvanRestoran);
        verify(this.restaurantRepository).save(this.nesacuvanRestoran);
        int id = pronadjena.getId();
        assertEquals(RESTAURANT_ID, id);
    }
}
