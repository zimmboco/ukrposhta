package com.org.ukrposhta.dto;

import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter
@Setter
public class User {
    private Long id;
    private String password;
    private String email;
    private Set<String> roles;
    private String levels;
}
