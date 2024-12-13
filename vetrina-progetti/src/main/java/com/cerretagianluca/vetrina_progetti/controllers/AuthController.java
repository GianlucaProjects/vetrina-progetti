package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.dtos.NewUserDTO;
import com.cerretagianluca.vetrina_progetti.dtos.UserLoginDTO;
import com.cerretagianluca.vetrina_progetti.dtos.UserLoginResponseDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.exceptions.BadRequestException;
import com.cerretagianluca.vetrina_progetti.services.AuthService;
import com.cerretagianluca.vetrina_progetti.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class AuthController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return this.authService.checkCredentialsAndGenerateToken(body);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {
        // @Validated serve per "attivare" le regole di validazione descritte nel DTO
        // BindingResult contiene l'esito della validazione, quindi sarà utile per capire se ci sono stati errori e quali essi siano
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.usersService.save(body);
    }
}
