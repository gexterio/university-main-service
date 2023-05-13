package ua.com.foxminded.university.unit.persistance.repository;

import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.university.annotation.RepositoryTest;


@RepositoryTest
@Sql("classpath:data/initTestDataScript.sql")
public abstract class RepositoryTestBase {
}
