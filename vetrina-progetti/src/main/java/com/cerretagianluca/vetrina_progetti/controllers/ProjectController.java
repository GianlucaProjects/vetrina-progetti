package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.dtos.ProjectDTO;
import com.cerretagianluca.vetrina_progetti.entites.ProjectEntity;
import com.cerretagianluca.vetrina_progetti.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ProjectEntity createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.create(projectDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<ProjectEntity> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ProjectEntity getProjectById(@PathVariable UUID id) {
        return projectService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteProject(@PathVariable UUID id) {
        projectService.delete(id);
    }
}