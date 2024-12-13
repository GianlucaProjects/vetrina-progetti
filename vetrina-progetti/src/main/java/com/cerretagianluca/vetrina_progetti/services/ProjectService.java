package com.cerretagianluca.vetrina_progetti.services;

import com.cerretagianluca.vetrina_progetti.dtos.ProjectDTO;
import com.cerretagianluca.vetrina_progetti.entites.Project;
import com.cerretagianluca.vetrina_progetti.exceptions.BadRequestException;
import com.cerretagianluca.vetrina_progetti.exceptions.NotFoundException;
import com.cerretagianluca.vetrina_progetti.repositories.ProjectRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;
    

    public Project create(ProjectDTO projectDTO) {
        String imageUrl = null;

        if (projectDTO.image() != null && !projectDTO.image().isEmpty()) {
            try {
                imageUrl = (String) cloudinaryUploader.uploader()
                        .upload(projectDTO.image().getBytes(), ObjectUtils.emptyMap())
                        .get("url");
            } catch (IOException e) {
                throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
            }
        }

        Project project = new Project(
                projectDTO.title(),
                projectDTO.description(),
                projectDTO.category(),
                imageUrl != null ? List.of(imageUrl) : new ArrayList<>()
        );

        return projectRepository.save(project);
    }


    public Page<Project> findAll(int page, int size, String sortBy) {
        if (size > 100) {
            size = 100; // Limitiamo la size max a 100 così da client non possono inserire numeri troppo grandi
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        // Pageable ci consente di configurare come devono essere paginati i risultati passando numero di pagina, numero elementi pagina e criterio di ordinamento
        return projectRepository.findAll(pageable);
    }

    public Project findById(UUID id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(UUID id) {
        Project found = this.findById(id);
        this.projectRepository.delete(found);
    }
}