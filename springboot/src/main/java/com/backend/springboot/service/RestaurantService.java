package com.backend.springboot.service;

import com.backend.springboot.model.Restaurant;
import com.backend.springboot.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Primary
@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll() { return this.restaurantRepository.findAll(); }

    public Restaurant findOne(int id) { return this.restaurantRepository.findById(id).orElse(null); }

    public Restaurant save(Restaurant restaurant) { return this.restaurantRepository.save(restaurant); }

}
