package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.RestaurantResponse;
import com.kenzie.appserver.service.RestaurantService;

import com.kenzie.appserver.service.model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> getRestaurant() {

        List<Restaurant> restaurants= restaurantService.findAll();

        List<RestaurantResponse> responses = restaurants.stream().map(this::restaurantToResponse).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }


    private RestaurantResponse restaurantToResponse (Restaurant restaurant) {
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setRestaurantId(restaurant.getRestaurantId());
        restaurantResponse.setRestaurantName(restaurant.getRestaurantName());
        restaurantResponse.setRating(restaurant.getRating());
        restaurantResponse.setStatus(restaurant.getStatus());
        restaurantResponse.setLocation(restaurant.getLocation());
        restaurantResponse.setCuisine(restaurant.getCuisine());
        restaurantResponse.setMenu(restaurant.getMenu());
        return restaurantResponse;
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<ExampleResponse> get(@PathVariable("id") String id) {
//
//        Restaurant restaurant = restaurantService.findById(id);
//        if (restaurant == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(restaurant.getId());
//        exampleResponse.setName(restaurant.getName());
//        return ResponseEntity.ok(exampleResponse);
//    }
//
//    @PostMapping
//    public ResponseEntity<ExampleResponse> addNewConcert(@RequestBody ExampleCreateRequest exampleCreateRequest) {
//        Restaurant restaurant = new Restaurant(randomUUID().toString(),
//                exampleCreateRequest.getName());
//        restaurantService.addNewExample(restaurant);
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(restaurant.getId());
//        exampleResponse.setName(restaurant.getName());
//
//        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getId())).body(exampleResponse);
//    }
}
