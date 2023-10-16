package com.luizreis.blogspring.dtos.follow;

import com.luizreis.blogspring.domain.follow.UserFollow;

import java.time.Instant;

public class FollowDTO {

    private Long userId;
    private Long followedUserId;
    private Instant followedAt;

    public FollowDTO(Long userId, Long followedUserId, Instant followedAt) {
        this.userId = userId;
        this.followedUserId = followedUserId;
        this.followedAt = followedAt;
    }

    public FollowDTO(UserFollow entity) {
        this.userId = entity.getUser().getId();
        this.followedUserId = entity.getFollowedUser().getId();
        this.followedAt = entity.getFollowedAt();
    }
    public Long getUserId() {
        return userId;
    }

    public Long getFollowedUserId() {
        return followedUserId;
    }

    public Instant getFollowedAt() {
        return followedAt;
    }
}
