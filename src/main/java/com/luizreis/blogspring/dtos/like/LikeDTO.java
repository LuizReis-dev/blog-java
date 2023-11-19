package com.luizreis.blogspring.dtos.like;

import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.projections.LikeProjection;

import java.time.Instant;

public class LikeDTO {

    private Long userId;
    private Long postId;
    private String username;
    private Instant likedAt;

    public LikeDTO(Long userId, Long postId, Instant likedAt, String username) {
        this.userId = userId;
        this.postId = postId;
        this.likedAt = likedAt;
        this.username = username;
    }

    public LikeDTO(Like entity){
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
        this.likedAt = entity.getLikedAt();
        this.username = entity.getUser().getUsername();
    }

    public LikeDTO(LikeProjection projection){
        this.userId = projection.getUserId();
        this.postId = projection.getPostId();
        this.likedAt = projection.getLikedAt();
        this.username = projection.getUsername();
    }
    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public Instant getLikedAt() {
        return likedAt;
    }

    public String getUsername() {
        return username;
    }
}
