package com.cerretagianluca.vetrina_progetti.controllers;

import com.cerretagianluca.vetrina_progetti.dtos.ChangePasswordRequestDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.responses.ChangePasswordResponse;
import com.cerretagianluca.vetrina_progetti.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/change")
    @PreAuthorize("hasAuthority('USER')")
    public ChangePasswordResponse changePassword(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody ChangePasswordRequestDTO request) {
        return passwordService.changePassword(currentAuthenticatedUser.getEmail(), request);
    }
}
