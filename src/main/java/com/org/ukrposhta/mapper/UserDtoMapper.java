package com.org.ukrposhta.mapper;

import com.org.ukrposhta.dto.User;
import com.org.ukrposhta.model.Level;
import com.org.ukrposhta.model.LevelName;
import com.org.ukrposhta.model.Role;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    com.org.ukrposhta.model.User toModel(User userDto);
    User toDto(com.org.ukrposhta.model.User user);
    List<User> toDtoList(List<com.org.ukrposhta.model.User> users);

    default Set<Role> map(Set<String> role) {
        return role.stream().map(it -> {
            Role role1 = new Role();
            role1.setRoleName(Role.RoleName.valueOf(it));
            return role1;
        }).collect(Collectors.toSet());
    }

    default Set<String> mapRole(Set<Role> roles) {
        return roles.stream().map(it -> it.getRoleName().getAuthority()).collect(Collectors.toSet());
    }

    default Level mapLevel(String level) {
        Level level1 = new Level();
        level1.setLevelName(LevelName.valueOf(level));
        return level1;
    }

    default String map(Level level) {
        return level.getLevelName().getAuthority();
    }
}
