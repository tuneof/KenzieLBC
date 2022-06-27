package com.kenzie.appserver.service.model;

public class Review {
    private final String review;
    private final String restaurantId;
    private final String userId;
    private final Integer rating;

    public Review(String restaurantId, String userId, Integer rating, String review) {
        this.review = review;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getRating() {
        return rating;
    }
}
