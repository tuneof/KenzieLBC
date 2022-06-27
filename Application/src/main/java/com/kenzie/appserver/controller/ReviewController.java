package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<List<ReviewResponse>> addNewReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        String restaurantId = reviewCreateRequest.getRestaurantId();
        String userId = reviewCreateRequest.getUserId();
        Integer rating = reviewCreateRequest.getRating();
        String review = reviewCreateRequest.getReview();

        Review userReview = new Review(restaurantId, userId, rating, review);
        reviewService.addReview(userReview);

//        ReviewResponse response = reviewToResponse();

//        return ResponseEntity.created(URI.create("/example/" + ReviewResponse.getId())).body(response);
        return null;
    }

    @PutMapping
    public ResponseEntity<ReviewResponse> updateReview() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<ReviewResponse> deleteReview() {
        return null;
    }

    private ReviewResponse reviewToResponse (Review review) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setRestaurantId(review.getRestaurantId());
        reviewResponse.setUserId(review.getUserId());
        reviewResponse.setRating(review.getRating());
        reviewResponse.setReview(review.getReview());

        return reviewResponse;
    }

}
