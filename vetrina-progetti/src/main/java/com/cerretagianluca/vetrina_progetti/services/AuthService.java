package com.cerretagianluca.vetrina_progetti.services;


import com.cerretagianluca.vetrina_progetti.dtos.UserLoginDTO;
import com.cerretagianluca.vetrina_progetti.dtos.UserLoginResponseDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.exceptions.UnauthorizedException;
import com.cerretagianluca.vetrina_progetti.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public UserLoginResponseDTO checkCredentialsAndGenerateToken(UserLoginDTO body) {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel DB se esiste un utente con l'email fornita
        User found = this.usersService.findByEmail(body.email());
        // 1.2 Verifico che la password di quell'utente corrisponda a quella fornita
        if (bcrypt.matches(body.password(), found.getPassword())) {
            // 2. Se sono OK --> Genero il token
            String accessToken = jwt.createToken(found);
            // 3. Ritorno il token
            return new UserLoginResponseDTO(accessToken, found.getName());
        } else {
            // 4. Se le credenziali sono errate --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}