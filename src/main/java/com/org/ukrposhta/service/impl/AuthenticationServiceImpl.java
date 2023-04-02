package com.org.ukrposhta.service.impl;

import com.org.ukrposhta.mapper.UserDtoMapper;
import com.org.ukrposhta.model.User;
import com.org.ukrposhta.service.AuthenticationService;
import com.org.ukrposhta.service.LevelService;
import com.org.ukrposhta.service.RoleService;
import com.org.ukrposhta.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final LevelService levelService;
    private final UserDtoMapper userDtoMapper;

    public AuthenticationServiceImpl(UserService userService, RoleService roleService,
                                     LevelService levelService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.levelService = levelService;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public User register(com.org.ukrposhta.dto.User user) {
        User user2 = userDtoMapper.toModel(user);
        return userService.save(user2);
    }
}
