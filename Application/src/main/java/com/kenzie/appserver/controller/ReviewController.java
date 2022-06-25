package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.controller.model.ExampleResponse;
import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.controller.model.reviewResponse;
import com.kenzie.appserver.service.model.Review;
import com.kenzie.appserver.service.reviewService;

import com.kenzie.appserver.service.model.review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

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
        String comment = reviewCreateRequest.getReview();

        Review review = new Review(restaurantId, userId, rating, comment);
        reviewService.addReview(review);

        ReviewResponse response = reviewToResponse(review);

        return ResponseEntity.created(URI.create("/example/" + ReviewResponse.getId())).body(response);
    }

    @PutMapping
    public ResponseEntity<ReviewResponse> updateReview() {

    }

    @DeleteMapping
    public ResponseEntity<ReviewResponse> deleteReview() {

    }

    private ReviewResponse reviewToResponse (Review review) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setRestaurantId(review.getRestaurantId());
        reviewResponse.setUserId(review.getUserId());
        reviewResponse.setRating(review.getRating());
        reviewResponse.setReview(review.getComment());
        return reviewResponse;
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<ExampleResponse> get(@PathVariable("id") String id) {
//
//        review review = reviewService.findById(id);
//        if (review == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(review.getId());
//        exampleResponse.setName(review.getName());
//        return ResponseEntity.ok(exampleResponse);
//    }
//
//    @PostMapping
//    public ResponseEntity<ExampleResponse> addNewConcert(@RequestBody ExampleCreateRequest exampleCreateRequest) {
//        review review = new review(randomUUID().toString(),
//                exampleCreateRequest.getName());
//        reviewService.addNewExample(review);
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(review.getId());
//        exampleResponse.setName(review.getName());
//
//        return ResponseEntity.created(URI.create("/example/" + exampleResponse.getId())).body(exampleResponse);
//    }
}
