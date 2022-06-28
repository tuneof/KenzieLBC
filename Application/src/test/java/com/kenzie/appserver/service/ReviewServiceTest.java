package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.repositories.model.ReviewRecord;
import com.kenzie.appserver.service.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        String rating = "3";

        Review userReview = new Review(restaurantId, userId, rating, review);
        ArgumentCaptor<ReviewRecord> reviewRecordCaptor = ArgumentCaptor.forClass(ReviewRecord.class);

        when(reviewRepository.save(any(ReviewRecord.class))).then(i -> i.getArgumentAt(0, ReviewRecord.class));

        // WHEN
        //change name of method to whatever it will be
        Review returnedReview = reviewService.addReview(userReview);

        // THEN
        verify(reviewRepository).save(reviewRecordCaptor.capture());
        ReviewRecord reviewRecord = reviewRecordCaptor.getValue();

        Assertions.assertNotNull(reviewRecord, "The review is saved");
        Assertions.assertEquals(reviewRecord.getRestaurantId(), userReview.getRestaurantId(), "The restaurant id matches");
        Assertions.assertEquals(reviewRecord.getUserId(), userReview.getUserId(), "The user id matches");
        Assertions.assertEquals(reviewRecord.getReview(), userReview.getReview(), "The review matches");
        Assertions.assertEquals(reviewRecord.getRating(), userReview.getRating(), "The rating matches");

        Assertions.assertNotNull(returnedReview, "The review is saved");
        Assertions.assertEquals(returnedReview.getRestaurantId(), userReview.getRestaurantId(), "The restaurant id matches");
        Assertions.assertEquals(returnedReview.getUserId(), userReview.getUserId(), "The user id matches");
        Assertions.assertEquals(returnedReview.getReview(), userReview.getReview(), "The review matches");
        Assertions.assertEquals(returnedReview.getRating(), userReview.getRating(), "The rating matches");
    }

    @Test
    void deleteReview_validReview_reviewDeleted() {
        //GIVEN
        String restaurantId = "1";
        String userId = "123";
        String rating = "3";
        String review = "This restaurant is very decorative.";

        Review userReview = new Review(restaurantId, userId, rating, review);
        ArgumentCaptor<ReviewRecord> reviewRecordCaptor = ArgumentCaptor.forClass(ReviewRecord.class);

        //WHEN
        reviewService.deleteReview(userReview);

        //THEN
        verify(reviewRepository).delete(reviewRecordCaptor.capture());
    }

    @Test
    void deleteReview_nullReview_throwsException() {
        //GIVEN

        //WHEN //THEN
        assertThrows(ReviewRecordNotFoundException.class, () -> reviewService.deleteReview(null));
    }
}
