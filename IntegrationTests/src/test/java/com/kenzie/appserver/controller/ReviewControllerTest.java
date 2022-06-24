package com.kenzie.appserver.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();

    @Test
    public void can_create_review() throws Exception {
        String restaurantId = "3";
        String userId = mockNeat.strings().valStr();
        String review = mockNeat.strings().valStr();
        int rating = 4;

        ReviewCreateRequest reviewCreateRequest = new ReviewCreateRequest();
        reviewCreateRequest.setRestaurantId(restaurantId);
        reviewCreateRequest.setUserId(userId);
        reviewCreateRequest.setReview(review);
        reviewCreateRequest.setRating(rating);

        //hopefully fixes with all classes implemented otherwise idk
        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/review")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(reviewCreateRequest)))
                .andExpect(jsonPath("restaurantId")
                        .value(is(restaurantId)))
                .andExpect(jsonPath("userId")
                        .value(is(userId)))
                .andExpect(jsonPath("review")
                        .value(is(review)))
                .andExpect(jsonPath("rating")
                        .value(is(rating)))
                .andExpect(status().isCreated());
    }
}
