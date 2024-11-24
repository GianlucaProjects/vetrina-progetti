package com.cerretagianluca.vetrina_progetti.services;

import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import com.cerretagianluca.vetrina_progetti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);

        return user.orElse(null);
    }

    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    public String getNameByEmail(String email) {
        return userRepository.findByEmail(email).getName();
    }
}
