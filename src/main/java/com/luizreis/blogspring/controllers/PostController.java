package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.comment.CommentDTO;
import com.luizreis.blogspring.dtos.like.LikeDTO;
import com.luizreis.blogspring.dtos.post.PostDTO;
import com.luizreis.blogspring.dtos.post.PostMinDTO;
import com.luizreis.blogspring.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PostDTO> insert(@Valid @RequestBody PostDTO dto) {
        return ResponseEntity.ok(service.insert(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/timeline")
    public ResponseEntity<List<PostMinDTO>> timeline() {
        return ResponseEntity.ok(service.getLoggedUserTimeline());
    }

}
