package com.org.ukrposhta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.org.ukrposhta.dto.User;
import com.org.ukrposhta.model.Level;
import com.org.ukrposhta.model.LevelName;
import com.org.ukrposhta.model.Role;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/scripts/init_users.sql")
class UserControllerTest {
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
    void shouldShowAllUsers() throws Exception {
        ResponseEntity<String> forEntity =
                testRestTemplate.getForEntity("/user/getAll", String.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
        System.out.println(forEntity.getBody());
    }

    @Test
    void shouldAddUser() throws Exception {
        com.org.ukrposhta.dto.User user = new User();
        Level level = new Level();
        level.setLevelName(LevelName.LEVEL_MIDDLE);
        Role role = new Role();
        role.setRoleName(Role.RoleName.ROLE_QA);
        user.setEmail("zimmboco");
        user.setPassword("1234567");
        user.setLevels(level.getLevelName().getAuthority());
        user.setRoles(Set.of(role.getRoleName().getAuthority()));
        ResponseEntity<com.org.ukrposhta.dto.User> forEntity =
                testRestTemplate.postForEntity("/register", user, com.org.ukrposhta.dto.User.class);
        System.out.println(forEntity);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }

    @Test
    void shouldShowUserByEmail() throws Exception {
        ResponseEntity<com.org.ukrposhta.dto.User> forEntity =
                testRestTemplate.getForEntity("/user/by-email?email=zimmboco1",
                        com.org.ukrposhta.dto.User.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }

    @Test
    void shouldShowUserById() throws Exception {
        ResponseEntity<com.org.ukrposhta.dto.User> forEntity =
                testRestTemplate.getForEntity("/user/1", com.org.ukrposhta.dto.User.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }

    @Test
    void shouldReturnAllUsersInProjectId() throws Exception {
        ResponseEntity<String> forEntity =
                testRestTemplate.getForEntity("/user/projects/1", String.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }

    @Test
    void findAllByProjects_Id() throws Exception {
        ResponseEntity<String> forEntity =
                testRestTemplate.getForEntity("/projects/1", String.class);
        Assertions.assertSame(HttpStatusCode.valueOf(200), forEntity.getStatusCode());
    }
}