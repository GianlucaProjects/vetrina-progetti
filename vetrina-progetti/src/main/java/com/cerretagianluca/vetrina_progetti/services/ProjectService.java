package com.cerretagianluca.vetrina_progetti.services;

import com.cerretagianluca.vetrina_progetti.dtos.ProjectDTO;
import com.cerretagianluca.vetrina_progetti.entites.ProjectEntity;
import com.cerretagianluca.vetrina_progetti.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity create(ProjectDTO projectDTO) {
        ProjectEntity project = new ProjectEntity(projectDTO.title(), projectDTO.description(), projectDTO.category(), projectDTO.images());
        return projectRepository.save(project);
    }

    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    public ProjectEntity findById(UUID id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void delete(UUID id) {
        projectRepository.deleteById(id);
    }
}