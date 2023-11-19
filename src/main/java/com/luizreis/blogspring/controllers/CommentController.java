package com.luizreis.blogspring.controllers;

import com.luizreis.blogspring.dtos.comment.CommentDTO;
import com.luizreis.blogspring.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> comment(@PathVariable Long postId, @Valid @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(service.create(postId, commentDTO));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> comment(@PathVariable Long commentId){
        service.delete(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long postId){
        return ResponseEntity.ok(service.getCommentsByPost(postId));
    }
}
