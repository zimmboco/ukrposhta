package com.org.ukrposhta.repository;

import com.org.ukrposhta.model.Role;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(Role.RoleName roleName);

    Set<Role> findByRoleNameIn(Set<Role.RoleName> roleNames);
}
