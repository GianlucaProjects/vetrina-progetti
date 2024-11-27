package com.cerretagianluca.vetrina_progetti.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProjectDTO(
        @NotEmpty(message = "Il titolo è obbligatorio!")
        @Size(min = 3, max = 50, message = "Il titolo deve essere lungo tra 3 e 50 caratteri!")
        String title,
        @NotEmpty(message = "La descrizione è obbligatoria!")
        @Size(min = 10, max = 100, message = "La descrizione deve essere lunga tra 10 e 100 caratteri!")
        String description,
        @NotEmpty(message = "La categoria è obbligatoria!")
        @Size(min = 10, max = 15, message = "La categoria deve essere lunga tra 3 e 15 caratteri!")
        String category,
        String image
) {
}