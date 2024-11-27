package com.cerretagianluca.vetrina_progetti.dtos;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}