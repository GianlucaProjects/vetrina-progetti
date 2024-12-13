package com.cerretagianluca.vetrina_progetti.services;

import com.cerretagianluca.vetrina_progetti.dtos.ChangePasswordRequestDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.repositories.UsersRepository;
import com.cerretagianluca.vetrina_progetti.responses.ChangePasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ChangePasswordResponse changePassword(String email, ChangePasswordRequestDTO request) {
        User user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato!"));

        user.setPassword(passwordEncoder.encode(request.password()));
        usersRepository.save(user);

        return new ChangePasswordResponse("Password cambiata con successo!");
    }
}
