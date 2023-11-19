package com.luizreis.blogspring.projections;

import java.time.Instant;

public interface LikeProjection {

    Long getUserId();
    String getUsername();
    Long getPostId();
    Instant getLikedAt();
}
