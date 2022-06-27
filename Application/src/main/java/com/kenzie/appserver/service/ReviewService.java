package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.RestaurantRepository;
import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.repositories.model.ReviewRecord;
import com.kenzie.appserver.service.model.Restaurant;
import com.kenzie.appserver.service.model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

//    public List<Review> findByRestaurantId(String restaurantId) {
//        List<Review> listOfReviews = new ArrayList<>();
//        reviewRepository
//                .findById(restaurantId)
//                .forEach(review -> new Review(
//                        review.getRestaurantId(),
//                        review.getUserId(),
//                        review.getRating(),
//                        review.getReview()))
//                .orElse(null);
//
//        return listOfReviews;
//    }

    public Review findByRestaurantId(String restaurantId) {
        return reviewRepository
                .findById(restaurantId)
                .map(review -> toReview(review))
                .orElse(null);
    }

    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        reviewRepository
                .findAll()
                .forEach(review -> reviews
                        .add(toReview(review)));
        return reviews;
    }

    public Review addReview(Review review) {
        ReviewRecord reviewRecord = toReviewRecord(review);
        reviewRepository.save(reviewRecord);
        return review;
    }

    private ReviewRecord toReviewRecord(Review review) {
        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setRestaurantId(review.getRestaurantId());
        reviewRecord.setUserId(review.getUserId());
        reviewRecord.setRating(review.getRating());
        reviewRecord.setReview(review.getReview());
        return reviewRecord;
    }

    private Review toReview(ReviewRecord reviewRecord) {
        Review review = new Review(
                reviewRecord.getRestaurantId(),
                reviewRecord.getUserId(),
                reviewRecord.getRating(),
                reviewRecord.getReview());
        return review;
    }

    public void deleteReview(Review review) {
        ReviewRecord reviewRecord = toReviewRecord(review);
        reviewRepository.delete(reviewRecord);
    }

    public ReviewRecord updateReview(ReviewRecord reviewRecord) {
        return reviewRepository.findById(reviewRecord.getRestaurantId())
                .map(review -> {
                    review.setRestaurantId(reviewRecord.getRestaurantId());
                    review.setUserId(reviewRecord.getUserId());
                    review.setRating(reviewRecord.getRating());
                    review.setReview(reviewRecord.getReview());
                    return reviewRepository.save(reviewRecord);
                })
                .orElseGet(() -> {
                    return reviewRepository.save(reviewRecord);
                });
    }

}
