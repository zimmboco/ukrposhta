package com.org.ukrposhta.service.impl;

import com.org.ukrposhta.model.Project;
import com.org.ukrposhta.repository.ProjectRepository;
import com.org.ukrposhta.service.ProjectService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project get(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> findAllByUsersId(Long id) {
        return projectRepository.findAllByUsers_Id(id);
    }
    @Override
    public Project update(Project project) {
        Project project1 = new Project();
        project1.setId(project.getId());
        project1.setTopic(project.getTopic());
        project1.setNameProject(project.getNameProject());
        project1.setUsers(project.getUsers());
        return projectRepository.save(project1);
    }
}
