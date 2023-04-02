package com.org.ukrposhta.controller;

import com.org.ukrposhta.dto.User;
import com.org.ukrposhta.mapper.UserDtoMapper;
import com.org.ukrposhta.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserDtoMapper mapper;

    public AuthenticationController(AuthenticationService authenticationService, UserDtoMapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        com.org.ukrposhta.model.User register = authenticationService.register(user);
        User user1 = mapper.toDto(register);
        return ResponseEntity.ok(user1);
    }
}
