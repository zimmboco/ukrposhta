package com.org.ukrposhta.service;

import com.org.ukrposhta.model.Role;
import java.util.Set;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);

    Set<Role> getByNames(Set<String> roleNames);
}
