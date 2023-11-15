package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/like")
public class LikeController {
    private final LikeService service;

    public LikeController(LikeService service) {
        this.service = service;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<LikeDTO> like(@PathVariable Long postId){
        return ResponseEntity.ok(service.create(postId));
    }
}
