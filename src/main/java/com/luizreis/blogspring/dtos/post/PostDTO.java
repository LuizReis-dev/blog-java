package com.luizreis.blogspring.dtos.post;

import com.luizreis.blogspring.domain.post.Post;
import jakarta.validation.constraints.NotEmpty;

public class PostDTO {

    private Long id;
    @NotEmpty(message = "Text can't be empty")
    private String text;
    private String author;

    public PostDTO(Long id, String text, String author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }
    public PostDTO(Post entity){
        this.id = entity.getId();
        this.text = entity.getText();
        this.author = entity.getAuthor().getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
