package com.cerretagianluca.vetrina_progetti.repositories;

import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);
}
