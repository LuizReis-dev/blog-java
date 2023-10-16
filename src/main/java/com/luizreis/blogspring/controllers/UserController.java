package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.follow.FollowDTO;
import com.luizreis.blogspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/follow/{followedUserId}")
    public ResponseEntity<FollowDTO> follow(@PathVariable Long followedUserId){
        return ResponseEntity.ok(service.follow(followedUserId));
    }

}
