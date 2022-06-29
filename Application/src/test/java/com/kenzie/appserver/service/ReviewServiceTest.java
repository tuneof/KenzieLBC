package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.repositories.model.ReviewRecord;
import com.kenzie.appserver.service.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

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

    @Test
    void updateReview_validInfo_reviewUpdated() {
        // GIVEN
        String restaurantId1 = "1";
        String userId1 = "123";
        String rating1 = "5";
        String review1 = "the food is delicious";

        Review userReview = new Review(restaurantId1, userId1, rating1, review1);
        ReviewRecord record1 = new ReviewRecord();
        record1.setRestaurantId(restaurantId1);
        record1.setUserId(userId1);
        record1.setRating(rating1);
        record1.setReview(review1);

        ArgumentCaptor<ReviewRecord> reviewRecordCaptor = ArgumentCaptor.forClass(ReviewRecord.class);
        when(reviewRepository.save(any())).thenReturn(record1);

        // WHEN
        Review returnedReview = reviewService.updateReview(userReview);

        // THEN
        verify(reviewRepository).save(reviewRecordCaptor.capture());
        ReviewRecord reviewRecord = reviewRecordCaptor.getValue();

        Assertions.assertNotNull(reviewRecord, "The review is updated");
        Assertions.assertEquals(reviewRecord.getRestaurantId(), returnedReview.getRestaurantId(), "The restaurant id matches");
        Assertions.assertEquals(reviewRecord.getUserId(), returnedReview.getUserId(), "The user id matches");
        Assertions.assertEquals(reviewRecord.getReview(), returnedReview.getReview(), "The review matches");
        Assertions.assertEquals(reviewRecord.getRating(), returnedReview.getRating(), "The rating matches");
    }

    @Test
    void updateReview_nullReview_throwsException() {
        //GIVEN

        //WHEN //THEN
        assertThrows(ReviewRecordNotFoundException.class, () -> reviewService.updateReview(null));
    }
}
