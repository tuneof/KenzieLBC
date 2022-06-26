package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.service.model.Restaurant;
import com.kenzie.appserver.service.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review findById(String restaurantId) {
        Review reviewFromBackend = reviewRepository
                .findById(restaurantId)
                .map(review -> new Review(
                        review.getRestaurantId(),
                        review.getUserId(),
                        review.getRating(),
                        review.getReview()))
                .orElse(null);

        return reviewFromBackend;
    }

    public List<Review> findAll() {
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
