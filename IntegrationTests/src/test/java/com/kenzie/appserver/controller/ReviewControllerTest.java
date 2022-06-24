package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();
    private final MockNeat mockNeat = MockNeat.threadLocal();

    //QueryUtility?
}
