package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class ReviewCreateRequest {

    @NotEmpty
    @JsonProperty("restaurantId")
    private String restaurantId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("review")
    private String review;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setComment(String comment) {
        this.review = review;
    }
}
