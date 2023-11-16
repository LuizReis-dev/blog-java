package com.luizreis.blogspring.dtos.post;

import com.luizreis.blogspring.projections.PostMinProjection;

import java.time.Instant;

public class PostMinDTO {

    private Long id;
    private Instant postedAt;
    private String text;
    private Long authorId;
    private String username;
    private Integer totalLikes;
    private Integer totalComments;

    public PostMinDTO(Long id, Instant postedAt, String text, Long authorId, String username, Integer totalLikes, Integer totalComments) {
        this.id = id;
        this.postedAt = postedAt;
        this.text = text;
        this.authorId = authorId;
        this.username = username;
        this.totalLikes = totalLikes;
        this.totalComments = totalComments;
    }
    public PostMinDTO(PostMinProjection projection) {
        this.id = projection.getId();
        this.postedAt = projection.getPostedAt();
        this.text = projection.getText();
        this.authorId = projection.getAuthorId();
        this.username = projection.getUsername();
        this.totalLikes = projection.getLikes();
        this.totalComments = projection.getComments();
    }

    public Long getId() {
        return id;
    }

    public Instant getPostedAt() {
        return postedAt;
    }

    public String getText() {
        return text;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getUsername() {
        return username;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public Integer getTotalComments() {
        return totalComments;
    }
}
