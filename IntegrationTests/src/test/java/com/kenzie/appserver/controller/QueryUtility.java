package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class QueryUtility {
    public RestaurantControllerClient restaurantControllerClient;
    public ReviewControllerClient reviewControllerClient;

    private final MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    public QueryUtility(MockMvc mvc) {
        this.mvc = mvc;
        this.restaurantControllerClient = new RestaurantControllerClient();
        this.reviewControllerClient = new ReviewControllerClient();
    }

    public class RestaurantControllerClient {
        public ResultActions getById(String id) throws Exception {
            return mvc.perform(get("/all", id)
                    .accept(MediaType.APPLICATION_JSON));
        }

    }

    public class ReviewControllerClient {
        public ResultActions methodName(String id) throws Exception {
            return mvc.perform(get("/ /{id}", id)
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions createReview(String review) throws Exception {
            return mvc.perform(post("/ /{id}", review)
                    .accept(MediaType.APPLICATION_JSON));
        }

        public ResultActions deleteReview(String id) throws Exception {
            return mvc.perform(delete("/ /", id));
        }
    }
}
