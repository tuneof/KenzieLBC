package com.kenzie.appserver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ReviewServiceTest {
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    //rename to whatever actual method is called
    void canCreateNewReview() {
        // GIVEN
        String restaurantId = "4";
        String userId = "5";
        String review = "very cool place, nice food";
        int rating = 3;

        Review review = new Review(restaurantId, userId, review, rating);
        ArgumentCaptor<ReviewRecord> reviewRecordCaptor = ArgumentCaptor.forClass(ReviewRecord.class);

        when(reviewRepository.save(any(ReviewRecord.class))).then(i -> i.getArgumentAt(0, ReviewRecord.class));

        // WHEN
        //change name of method to whatever it will be
        Review returnedReview = reviewServce.createAddNewReview(review);

        // THEN
        verify(reviewRepository).save(reviewRecordCaptor.capture());
        ReviewRecord reviewRecord = reviewRecordCaptor.getValue();

        Assertions.assertNotNull(reviewRecord, "The review is saved");
        Assertions.assertEquals(reviewRecord.getRestaurantId(), review.getRestaurantId(), "The restaurant id matches");
        Assertions.assertEquals(reviewRecord.getUserId(), review.getUserId(), "The user id matches");
        Assertions.assertEquals(reviewRecord.getReview(), review.getReview(), "The review matches");
        Assertions.assertEquals(reviewRecord.getRating(), review.getRating(), "The rating matches");

        Assertions.assertNotNull(returnedReview, "The review is saved");
        Assertions.assertEquals(returnedReview.getRestaurantId(), review.getRestaurantId(), "The restaurant id matches");
        Assertions.assertEquals(returnedReview.getUserId(), review.getUserId(), "The user id matches");
        Assertions.assertEquals(returnedReview.getReview(), review.getReview(), "The review matches");
        Assertions.assertEquals(returnedReview.getRating(), review.getRating(), "The rating matches");
    }
}
