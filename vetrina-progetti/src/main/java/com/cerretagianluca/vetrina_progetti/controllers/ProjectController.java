package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.dtos.ProjectDTO;
import com.cerretagianluca.vetrina_progetti.entites.Project;
import com.cerretagianluca.vetrina_progetti.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Project createProject(@ModelAttribute ProjectDTO projectDTO) {
        return projectService.create(projectDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public Page<Project> getAllProjects(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        return projectService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Project getProjectById(@PathVariable UUID id) {
        return projectService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteProject(@PathVariable UUID id) {
        projectService.delete(id);
    }
}