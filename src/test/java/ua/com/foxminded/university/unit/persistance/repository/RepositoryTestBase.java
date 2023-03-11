package ua.com.foxminded.university.unit.persistance.repository;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import ua.com.foxminded.university.annotation.RepositoryTest;


@RepositoryTest
@Sql("classpath:data/initTestDataScript.sql")
public abstract class RepositoryTestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.1");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url ", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

}
