package com.org.ukrposhta.repository;

import com.org.ukrposhta.model.Project;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
class ProjectRepositoryTest {

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
    private ProjectRepository projectRepository;

    @Test
    void shouldReturnAllProjectsByUser1() {
        List<Project> allByUsers = projectRepository.findAllByUsers_Id(1L);
        Assertions.assertEquals(1, allByUsers.size());
    }

    @Test
    void shouldReturnAllProjectsByUser2() {
        List<Project> allByUsers = projectRepository.findAllByUsers_Id(2L);
        Assertions.assertEquals(allByUsers.get(0).getTopic(), "math");
        Assertions.assertEquals(1, allByUsers.size());
    }

}