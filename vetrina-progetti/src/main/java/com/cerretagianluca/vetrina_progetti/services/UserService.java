package com.cerretagianluca.vetrina_progetti.services;

import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import com.cerretagianluca.vetrina_progetti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
