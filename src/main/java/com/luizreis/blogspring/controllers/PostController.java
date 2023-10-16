package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PostDTO> insert(@Valid @RequestBody PostDTO dto){
        return ResponseEntity.ok(service.insert(dto));
    }

}