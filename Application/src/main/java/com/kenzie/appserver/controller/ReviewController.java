package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> addNewReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        Review userReview = new Review(reviewCreateRequest.getRestaurantId(), reviewCreateRequest.getUserId(),
                reviewCreateRequest.getRating(), reviewCreateRequest.getReview());

        try {
            reviewService.addReview(userReview);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        ReviewResponse response = reviewToResponse(userReview);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<ReviewResponse> updateReview() {
        return null;
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity deleteReview(@PathVariable("userid") String userId) {
        List<Review> reviewsList = reviewService.findAll();
        for (Review review: reviewsList) {
            if (review.getUserId().equals(userId)) {
                reviewService.deleteReview(review);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.badRequest().body("Unable to find selected Review");
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
