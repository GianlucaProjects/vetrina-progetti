package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.auth.JWTTools;
import com.cerretagianluca.vetrina_progetti.dtos.LoginDTO;
import com.cerretagianluca.vetrina_progetti.dtos.SignupDTO;
import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import com.cerretagianluca.vetrina_progetti.responses.LoginResponseDto;
import com.cerretagianluca.vetrina_progetti.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDTO body, @AuthenticationPrincipal UserEntity me) throws BadRequestException {
        UserEntity user = this.userService.findByEmail(body.email());

        if (encoder.matches(body.password(), user.getPassword())) {
            return new LoginResponseDto(jwtTools.createToken(user.getId()), userService.getNameByEmail(body.email()));
        } else {
            throw new BadRequestException("Password sbagliata!");
        }
    }

    @PostMapping("/signup")
    public UserEntity signup(@RequestBody SignupDTO body) throws BadRequestException {
        UserEntity user = new UserEntity(body.name(), body.username(), body.email(), encoder.encode(body.password()));
        return this.userService.create(user);
    }
}
