package com.org.ukrposhta.model;

import org.springframework.security.core.GrantedAuthority;

public enum LevelName implements GrantedAuthority {
    LEVEL_JUNIOR, LEVEL_MIDDLE, LEVEL_SENIOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
