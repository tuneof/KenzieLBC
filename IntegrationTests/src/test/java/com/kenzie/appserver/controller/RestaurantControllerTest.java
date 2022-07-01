package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.RestaurantService;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    RestaurantService restaurantService;

    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void get_restaurants() throws Exception {
        queryUtility.restaurantControllerClient.getRestaurants()
                .andExpect(status().isOk());
    }

}