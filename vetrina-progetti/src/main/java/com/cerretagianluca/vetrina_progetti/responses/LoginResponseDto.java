package com.cerretagianluca.vetrina_progetti.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private String name;
}
