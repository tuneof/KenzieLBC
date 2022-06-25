package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.model.RestaurantRecord;
import com.kenzie.appserver.service.model.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {
    private RestaurantRepository restaurantRepository;
    private RestaurantService restaurantService;

    @BeforeEach
    void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        restaurantService = new RestaurantService(restaurantRepository);
    }
    /** ------------------------------------------------------------------------
     *  restaurantService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById_validId_ReturnsRestaurant() {
        // GIVEN
        String id = randomUUID().toString();
        String name = "Restaurant Name";
        int rating = 3;
        String status = "active";
        String cuisine = "cuisine";
        String location = "location";
        List<String> menu = new ArrayList<>();
        menu.add("fries");
        menu.add("shake");

        RestaurantRecord record = new RestaurantRecord();
        record.setRestaurantId(id);
        record.setRestaurantName(name);
        record.setRating(rating);
        record.setStatus(status);
        record.setCuisine(cuisine);
        record.setLocation(location);
        record.setMenu(menu);

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(record));

        // WHEN
        Restaurant restaurant = restaurantService.findById(id);

        // THEN
        Assertions.assertNotNull(restaurant, "The object is returned");
        Assertions.assertEquals(record.getRestaurantId(), restaurant.getRestaurantId(), "Expected record and restaurant ids to match");
        Assertions.assertEquals(record.getRestaurantName(), restaurant.getRestaurantName(), "Expected the record-restaurant and restaurant names to match");
    }
}
