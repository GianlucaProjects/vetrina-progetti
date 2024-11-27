package com.cerretagianluca.vetrina_progetti.dtos;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorsResponseWithErrorsListDTO(String message, LocalDateTime timestamp, Map<String, String> errors) {
}