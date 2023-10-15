package com.luizreis.blogspring.services;

import com.luizreis.blogspring.domain.user.User;
import com.luizreis.blogspring.repositories.UserRepository;
import com.luizreis.blogspring.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = repository.findByUsername(userName)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return user;
    }
}
