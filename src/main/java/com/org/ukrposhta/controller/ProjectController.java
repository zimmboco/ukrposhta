package com.org.ukrposhta.controller;

import com.org.ukrposhta.dto.Project;
import com.org.ukrposhta.mapper.ProjectDtoMapper;
import com.org.ukrposhta.service.ProjectService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectDtoMapper mapper;

    public ProjectController(ProjectService projectService, ProjectDtoMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    ResponseEntity<Project> addProject(@RequestBody Project project) {
        com.org.ukrposhta.model.Project project1 = mapper.toModel(project);
        com.org.ukrposhta.model.Project saveModel = projectService.save(project1);
        Project saveDto = mapper.toDto(saveModel);
        return ResponseEntity.ok(saveDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<Project> getById(@PathVariable Long id) {
        com.org.ukrposhta.model.Project project = projectService.get(id);
        Project projectToDto = mapper.toDto(project);
        return ResponseEntity.ok(projectToDto);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Project>> getAll() {
        List<com.org.ukrposhta.model.Project> allProjects = projectService.getAll();
        List<Project> projects = mapper.toDtoList(allProjects);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/update")
    ResponseEntity<Project> update(@RequestBody Project project) {
        com.org.ukrposhta.model.Project project1 = mapper.toModel(project);
        com.org.ukrposhta.model.Project update = projectService.update(project1);
        return ResponseEntity.ok(mapper.toDto(update));
    }

    @DeleteMapping("/{id}")
    void deleteByID(@PathVariable Long id) {
        projectService.deleteByID(id);
    }

    @GetMapping("/projects-user/{id}")
    ResponseEntity<List<Project>> getAllProjectsByUser(@PathVariable Long id) {
        List<com.org.ukrposhta.model.Project> allByUsersId = projectService.findAllByUsersId(id);
        List<Project> projects = mapper.toDtoList(allByUsersId);
        return ResponseEntity.ok(projects);
    }
}
