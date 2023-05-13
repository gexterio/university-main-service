package ua.com.foxminded.university.integration.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.university.annotation.ControllerIntegrationTest;

import java.util.Collections;
import java.util.List;

@ControllerIntegrationTest
@Sql("classpath:data/initIntegrationTestDataScript.sql")
@WithMockUser(username = "admin@test.com", password = "pass", roles = "ADMIN")
public abstract class RestControllerIntegrationTestBase {

    @Autowired
    public MockMvc mockMvc;

    protected UserDetails userAdmin = new User("admin@test.com", "pass", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    protected UserDetails userStudent = new User("JohnSmith573@email.com", "pass", Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")));
    protected UserDetails userTeacher = new User("AndrewMoore600@email.com", "pass", Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER")));

    List<UserDetails> testUsers = List.of(userAdmin,userStudent,userTeacher);

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
