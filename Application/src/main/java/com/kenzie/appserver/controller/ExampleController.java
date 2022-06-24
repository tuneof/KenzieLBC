package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.controller.model.ExampleResponse;
import com.kenzie.appserver.service.RestaurantService;

import com.kenzie.appserver.service.model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/example")
public class ExampleController {

    private RestaurantService restaurantService;

    ExampleController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExampleResponse> get(@PathVariable("id") String id) {

        Restaurant restaurant = restaurantService.findById(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }

        ExampleResponse exampleResponse = new ExampleResponse();
        exampleResponse.setId(restaurant.getId());
        exampleResponse.setName(restaurant.getName());
        return ResponseEntity.ok(exampleResponse);
    }

    @PostMapping
    public ResponseEntity<ExampleResponse> addNewConcert(@RequestBody ExampleCreateRequest exampleCreateRequest) {
        Restaurant restaurant = new Restaurant(randomUUID().toString(),
                exampleCreateRequest.getName());
        restaurantService.addNewExample(restaurant);

        ExampleResponse exampleResponse = new ExampleResponse();
        exampleResponse.setId(restaurant.getId());
        exampleResponse.setName(restaurant.getName());

        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getId())).body(exampleResponse);
    }
}
