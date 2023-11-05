package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/like/{postId}")
    public ResponseEntity<LikeDTO> like(@PathVariable Long postId){
        return ResponseEntity.ok(service.like(postId));
    }
}
