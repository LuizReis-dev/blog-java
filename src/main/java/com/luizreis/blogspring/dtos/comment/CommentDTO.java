package com.luizreis.blogspring.dtos.comment;

import com.luizreis.blogspring.domain.comment.Comment;
import com.luizreis.blogspring.projections.CommentProjection;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public class CommentDTO {

    private Long id;
    private Long postId;
    @NotEmpty(message = "Text can't be empty")
    private String text;
    private String author;
    private Long authorId;
    private Instant postedAt;


    public CommentDTO(Long id, Long postId, String text, String author, Instant postedAt, Long authorId) {
        this.id = id;
        this.postId = postId;
        this.text = text;
        this.author = author;
        this.postedAt = postedAt;
        this.authorId = authorId;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.text = comment.getText();
        this.author = comment.getAuthor().getUsername();
        this.postedAt = comment.getPostedAt();
        this.authorId = comment.getAuthor().getId();
    }

    public CommentDTO(CommentProjection projection) {
        this.id = projection.getId();
        this.postId = projection.getPostId();
        this.text = projection.getText();
        this.author = projection.getAuthor();
        this.postedAt = projection.getPostedAt();
        this.authorId = projection.getAuthorId();
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getPostedAt(){
        return postedAt;
    }

    public Long getAuthorId() {
        return authorId;
    }
}