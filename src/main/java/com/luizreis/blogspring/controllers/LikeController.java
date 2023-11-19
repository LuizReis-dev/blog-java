package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/like")
public class LikeController {
    private final LikeService service;

    public LikeController(LikeService service) {
        this.service = service;
    }

    @PostMapping(value = "/{postId}")
    public ResponseEntity<LikeDTO> like(@PathVariable Long postId){
        return ResponseEntity.ok(service.create(postId));
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId){
        service.delete(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<LikeDTO>> getLikesByPost(@PathVariable Long postId){
        return ResponseEntity.ok(service.getLikesByPost(postId));
    }
}
