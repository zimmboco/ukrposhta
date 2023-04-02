package com.org.ukrposhta.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.org.ukrposhta.model.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/scripts/init_users.sql")
class UserRepositoryTest {
    @Container
    static MySQLContainer<?> database = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("springboot")
            .withPassword("springboot")
            .withUsername("springboot");
    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnFirstUserByEmail() {
        Optional<User> actual = userRepository.findByEmail("zimmboco");
        assertEquals("zimmboco", actual.get().getEmail());
    }
    @Test
    void shouldReturnSecondUserByEmail() {
        Optional<User> actual = userRepository.findByEmail("zimmboco1");
        assertEquals("zimmboco1", actual.get().getEmail());
    }

    @Test
    void shouldReturnAllUsersInProjectsById() {
        List<User> actual = userRepository.findAllByProjects_Id(1L);
        assertEquals(2, actual.size());
    }
}