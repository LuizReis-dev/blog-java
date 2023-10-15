package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.auth.LoginDTO;
import com.luizreis.blogspring.dtos.auth.SignupDTO;
import com.luizreis.blogspring.dtos.auth.TokenDTO;
import com.luizreis.blogspring.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<TokenDTO> register(@RequestBody SignupDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }
}
