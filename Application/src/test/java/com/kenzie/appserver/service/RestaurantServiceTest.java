package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.service.model.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
     *  exampleService.findById
     *  ------------------------------------------------------------------------ **/

    @Test
    void findById() {
        // GIVEN
        String id = randomUUID().toString();

        ExampleRecord record = new ExampleRecord();
        record.setId(id);
        record.setName("concertname");

        // WHEN
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(record));
        Restaurant restaurant = restaurantService.findById(id);

        // THEN
        Assertions.assertNotNull(restaurant, "The object is returned");
        Assertions.assertEquals(record.getId(), restaurant.getId(), "The id matches");
        Assertions.assertEquals(record.getName(), restaurant.getName(), "The name matches");
    }

    @Test
    void findByConcertId_invalid() {
        // GIVEN
        String id = randomUUID().toString();

        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Restaurant restaurant = restaurantService.findById(id);

        // THEN
        Assertions.assertNull(restaurant, "The example is null when not found");
    }

}
