package com.cerretagianluca.vetrina_progetti.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String category;

    @ElementCollection
    private List<String> images;

    public Project(String title, String description, String category, List<String> images) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.images = images;
    }

    public Project() {
    }
}