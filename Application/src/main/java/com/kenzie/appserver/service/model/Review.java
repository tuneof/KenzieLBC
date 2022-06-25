package com.kenzie.appserver.service.model;

public class Review {
    private final String comment;
    private final String restaurantId;
    private final String userId;
    private final Integer rating;

    public Review(String restaurantId, String userId, Integer rating, String comment) {
        this.comment = comment;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.rating = rating;
    }

    public String getComment() {
        return comment;
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
