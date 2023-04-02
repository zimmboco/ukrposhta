package com.org.ukrposhta.service.impl;

import com.org.ukrposhta.model.Role;
import com.org.ukrposhta.repository.RoleRepository;
import com.org.ukrposhta.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.findByRoleName(Role.RoleName.valueOf(roleName));
    }
    @Override
    public Set<Role> getByNames(Set<String> roleNames) {
        return roleRepository.findByRoleNameIn(roleNames.stream().map(Role.RoleName::valueOf).collect(
                Collectors.toSet()));
    }
}
