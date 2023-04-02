package com.org.ukrposhta.controller;

import com.org.ukrposhta.dto.User;
import com.org.ukrposhta.mapper.UserDtoMapper;
import com.org.ukrposhta.service.UserService;
import java.lang.reflect.Array;
import java.util.List;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper mapper;

    public UserController(UserService userService, UserDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        com.org.ukrposhta.model.User byID = userService.getByID(id);
        User user = mapper.toDto(byID);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
//    @Secured({"ROLE_MANAGER"})
    public ResponseEntity<List<User>> getAll() {
        List<com.org.ukrposhta.model.User> all = userService.getAll();
        return ResponseEntity.ok(mapper.toDtoList(all));
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        com.org.ukrposhta.model.User byEmail = userService.findByEmail(email);
        User user = mapper.toDto(byEmail);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/update")
    ResponseEntity<User> updateUser(@RequestBody User user) {
        com.org.ukrposhta.model.User user1 = mapper.toModel(user);
        com.org.ukrposhta.model.User update = userService.update(user1);
        User user2 = mapper.toDto(update);
        return ResponseEntity.ok(user2);
    }

    @GetMapping("/projects/{id}")
    ResponseEntity<List<User>> findAllByProjects_Id(@PathVariable Long id) {
        List<com.org.ukrposhta.model.User> allByProjectsId = userService.findAllByProjects_Id(id);
        List<User> users = mapper.toDtoList(allByProjectsId);
        return ResponseEntity.ok(users);
    }
}
