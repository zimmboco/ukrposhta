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
public class Project {
    private Long id;
    private String nameProject;
    private String topic;
    private Set<User> users;
}
