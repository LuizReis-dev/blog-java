package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.auth.SignupDTO;
import com.luizreis.blogspring.dtos.auth.TokenDTO;
import com.luizreis.blogspring.infra.security.JwtService;
import com.luizreis.blogspring.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenDTO register(SignupDTO request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setCreatedAt(Instant.now());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new TokenDTO(user.getId(), jwtToken);
    }
}
