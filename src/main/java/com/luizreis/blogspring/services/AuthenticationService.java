package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.dtos.auth.LoginDTO;
import com.luizreis.blogspring.dtos.auth.SignupDTO;
import com.luizreis.blogspring.dtos.auth.TokenDTO;
import com.luizreis.blogspring.infra.security.JwtService;
import com.luizreis.blogspring.repositories.UserRepository;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class.getName());
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    public TokenDTO login(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        var jwtToken = jwtService.generateToken(user);

        return new TokenDTO(user.getId(), jwtToken);
    }
}
