package com.cerretagianluca.vetrina_progetti.repositories;

import com.cerretagianluca.vetrina_progetti.entites.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}