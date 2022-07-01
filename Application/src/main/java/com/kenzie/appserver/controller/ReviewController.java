package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.controller.model.ReviewUpdateRequest;
import com.kenzie.appserver.service.ReviewRecordNotFoundException;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody ReviewUpdateRequest reviewUpdateRequest){
        if (reviewUpdateRequest.getRestaurantId() == null || reviewUpdateRequest.getUserId() == null) {
            throw new ReviewRecordNotFoundException();
        }
        Review updatedReview = new Review(reviewUpdateRequest.getRestaurantId(), reviewUpdateRequest.getUserId(),
                reviewUpdateRequest.getRating(), reviewUpdateRequest.getReview());
        reviewService.updateReview(updatedReview);

        ReviewResponse reviewResponse = reviewToResponse(updatedReview);

        return ResponseEntity.ok().body(reviewResponse);
    }

    @DeleteMapping("/{restaurantId}/{userId}")
    public ResponseEntity deleteReview(@PathVariable("restaurantId") String restaurantId,
                                       @PathVariable("userId") String userId) {
        List<Review> reviewsList = reviewService.findByRestaurantId(restaurantId);
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
