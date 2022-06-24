package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.service.model.Restaurant;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant findById(String id) {
        //Once RestaurantModel/Record gets implemented
        Restaurant restaurantFromBackend = restaurantRepository
                .findById(id)
                .map(restaurant -> new Restaurant(restaurant.getRestaurantId(), restaurant.getRestaurantName(),
                        restaurant.getRating(), restaurant.getStatus(), restaurant.getCuisine(), restaurant.getLocation(),
                        restaurant.getMenu()))
                .orElse(null);

        return restaurantFromBackend;
    }

//    public Restaurant addNewExample(Restaurant restaurant) {
//        ExampleRecord exampleRecord = new ExampleRecord();
//        exampleRecord.setId(restaurant.getId());
//        exampleRecord.setName(restaurant.getName());
//        restaurantRepository.save(exampleRecord);
//        return restaurant;
//    }
}
