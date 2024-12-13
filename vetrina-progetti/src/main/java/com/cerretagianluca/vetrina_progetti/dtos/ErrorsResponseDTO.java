package com.cerretagianluca.vetrina_progetti.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp, HttpStatus status) {
}