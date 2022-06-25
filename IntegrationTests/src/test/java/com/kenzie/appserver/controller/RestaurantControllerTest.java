package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ExampleCreateRequest;
import com.kenzie.appserver.controller.model.RestaurantCreateRequest;
import com.kenzie.appserver.service.RestaurantService;
import com.kenzie.appserver.service.model.Restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
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
    public void getById_Exists() throws Exception {
        String id = UUID.randomUUID().toString();
        String name = mockNeat.strings().get();

        RestaurantCreateRequest restaurantCreateRequest = new RestaurantCreateRequest();
        restaurantCreateRequest.setRestaurantId(id);
        restaurantCreateRequest.setRestaurantName(name);

        queryUtility.restaurantControllerClient.getById(restaurantCreateRequest.getName())
                .andExpect(jsonPath("id")
                        .value(is(id)))
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(status().isOk());
    }

    @Test
    public void getById_invalidIdIsEmpty() throws Exception {
        String id = "";
        String name = mockNeat.strings().get();

        RestaurantCreateRequest restaurantCreateRequest = new RestaurantCreateRequest();
        restaurantCreateRequest.setRestaurantId(id);
        restaurantCreateRequest.setRestaurantName(name);

        queryUtility.restaurantControllerClient.getById(restaurantCreateRequest.getName())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getById_invalidIdIsNull() throws Exception {
        String id = null;
        String name = mockNeat.strings().get();

        RestaurantCreateRequest restaurantCreateRequest = new RestaurantCreateRequest();
        restaurantCreateRequest.setRestaurantId(id);
        restaurantCreateRequest.setRestaurantName(name);

        queryUtility.restaurantControllerClient.getById(restaurantCreateRequest.getName())
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void createExample_CreateSuccessful() throws Exception {
//        String name = mockNeat.strings().valStr();
//
//        ExampleCreateRequest exampleCreateRequest = new ExampleCreateRequest();
//        exampleCreateRequest.setName(name);
//
//        mapper.registerModule(new JavaTimeModule());
//
//        mvc.perform(post("/example")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(exampleCreateRequest)))
//                .andExpect(jsonPath("id")
//                        .exists())
//                .andExpect(jsonPath("name")
//                        .value(is(name)))
//                .andExpect(status().isCreated());
//    }
}