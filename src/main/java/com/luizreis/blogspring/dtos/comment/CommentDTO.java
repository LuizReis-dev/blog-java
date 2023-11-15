package com.luizreis.blogspring.dtos.comment;

import com.luizreis.blogspring.domain.comment.Comment;
import jakarta.validation.constraints.NotEmpty;

public class CommentDTO {

    private Long id;
    private Long postId;
    @NotEmpty(message = "Text can't be empty")
    private String text;
    private String author;

    public CommentDTO(Long id, Long postId, String text, String author) {
        this.id = id;
        this.postId = postId;
        this.text = text;
        this.author = author;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.text = comment.getText();
        this.author = comment.getAuthor().getUsername();
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
}
