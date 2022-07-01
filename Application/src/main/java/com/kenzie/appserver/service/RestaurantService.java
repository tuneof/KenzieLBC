package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.service.model.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant findById(String id) {
        Restaurant restaurantFromBackend = restaurantRepository
                .findById(id)
                .map(restaurant -> new Restaurant(restaurant.getRestaurantId(), restaurant.getRestaurantName(),
                        restaurant.getRating(), restaurant.getStatus(), restaurant.getCuisine(), restaurant.getLocation(),
                        restaurant.getMenu()))
                .orElse(null);

        return restaurantFromBackend;
    }

    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantRepository
                .findAll()
                .forEach(restaurant -> restaurants
                        .add(new Restaurant(
                                restaurant.getRestaurantId(),
                                restaurant.getRestaurantName(),
                                restaurant.getRating(),
                                restaurant.getStatus(),
                                restaurant.getCuisine(),
                                restaurant.getLocation(),
                                restaurant.getMenu())));

        return restaurants;
    }
}
