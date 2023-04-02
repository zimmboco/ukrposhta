package com.org.ukrposhta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    public enum RoleName implements GrantedAuthority {
        ROLE_QA, ROLE_MANAGER, ROLE_DEVELOPER, ROLE_DEVOPS, ROLE_USER;

        @Override
        public String getAuthority() {
            return this.name();
        }
    }
}