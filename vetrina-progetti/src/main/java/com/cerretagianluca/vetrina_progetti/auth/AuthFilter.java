package com.cerretagianluca.vetrina_progetti.auth;

import com.cerretagianluca.vetrina_progetti.entites.UserEntity;
import com.cerretagianluca.vetrina_progetti.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    JWTTools jwt;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");

        if (authToken == null || !authToken.startsWith("Bearer ")) {
            throw new RuntimeException("Token missing or malformed!");
        }

        String token = authToken.substring(7);
        jwt.verifyToken(token);

        String id = jwt.decodeToken(token);

        UserEntity user = this.userService.findById(UUID.fromString(id));

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);


    }

    protected boolean shouldNotFilter(HttpServletRequest req) {
        return new AntPathMatcher().match("/users/login", req.getServletPath()) ||
                new AntPathMatcher().match("/users/signup", req.getServletPath());
    }
}
