package com.luizreis.blogspring.dtos.like;

import com.luizreis.blogspring.domain.like.Like;

import java.time.Instant;

public class LikeDTO {

    private Long userId;
    private Long postId;
    private Instant likedAt;

    public LikeDTO(Long userId, Long postId, Instant likedAt) {
        this.userId = userId;
        this.postId = postId;
        this.likedAt = likedAt;
    }

    public LikeDTO(Like entity){
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
        this.likedAt = entity.getLikedAt();
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
}
