package com.org.ukrposhta.service;

import com.org.ukrposhta.model.Project;
import java.util.List;

public interface ProjectService {
    Project save(Project project);
    Project get(Long id);
    List<Project> getAll();
    void deleteByID(Long id);
    List<Project> findAllByUsersId(Long id);
    Project update(Project project);
}
