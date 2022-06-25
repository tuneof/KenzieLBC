package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.service.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review findById(String id) {
        Review reviewFromBackend = reviewRepository
                .findById(id)
                .map(review -> new Review(review.getRestaurantId(), review.getUserId, review.getReview))
                .orElse(null);

        return reviewFromBackend;
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

//    public Restaurant addNewExample(Restaurant restaurant) {
//        ExampleRecord exampleRecord = new ExampleRecord();
//        exampleRecord.setId(restaurant.getId());
//        exampleRecord.setName(restaurant.getName());
//        restaurantRepository.save(exampleRecord);
//        return restaurant;
//    }
}
