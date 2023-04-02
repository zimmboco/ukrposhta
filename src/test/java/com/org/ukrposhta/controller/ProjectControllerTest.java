package com.org.ukrposhta.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.org.ukrposhta.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/scripts/init_users.sql")
class ProjectControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
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

    @Test
    void addProject() {
        Project project = new Project();
        project.setNameProject("igor");
        project.setTopic("math");
        ResponseEntity<com.org.ukrposhta.dto.Project> projectResponseEntity =
                testRestTemplate.postForEntity("/project/create", project,
                        com.org.ukrposhta.dto.Project.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), projectResponseEntity.getStatusCode());
    }

    @Test
    void getById() {
        ResponseEntity<com.org.ukrposhta.dto.Project> forEntity =
                testRestTemplate.getForEntity("/project/1", com.org.ukrposhta.dto.Project.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
        System.out.println(forEntity.getBody());
    }

    @Test
    void getAll() {
        ResponseEntity<String> forEntity =
                testRestTemplate.getForEntity("/project/getAll", String.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }

    @Test
    void getAllProjectsByUser() {
        ResponseEntity<String> forEntity =
                testRestTemplate.getForEntity("/projects-user/1", String.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }
}