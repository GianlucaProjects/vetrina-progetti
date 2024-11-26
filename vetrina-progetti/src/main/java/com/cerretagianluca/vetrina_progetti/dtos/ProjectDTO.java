package com.cerretagianluca.vetrina_progetti.dtos;

import java.util.List;

public record ProjectDTO(String title, String description, String category, List<String> images) {
}