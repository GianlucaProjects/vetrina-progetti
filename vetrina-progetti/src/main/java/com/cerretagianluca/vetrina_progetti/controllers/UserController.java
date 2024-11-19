package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.auth.JWTTools;
import com.cerretagianluca.vetrina_progetti.dtos.LoginDTO;
import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import com.cerretagianluca.vetrina_progetti.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO body) throws BadRequestException {
        UserEntity user = this.userService.findByEmail(body.email());
        if (user.getPassword().equals(body.password())) {
            return jwtTools.createToken(user.getId());
        } else {
            throw new BadRequestException("Password sbagliata!");
        }
    }
}
