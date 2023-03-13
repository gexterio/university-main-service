package ua.com.foxminded.university.integration.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.university.annotation.ControllerIntegrationTest;

@ControllerIntegrationTest
@Sql("classpath:data/initTestDataScript.sql")
public abstract class RestControllerIntegrationTestBase {

    @Autowired
    public MockMvc mockMvc;

    public static String asJsonString(final Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
