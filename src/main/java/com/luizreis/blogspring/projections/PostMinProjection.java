package com.luizreis.blogspring.projections;

import java.time.Instant;

public interface PostMinProjection {

    Long getId();
    Instant getPostedAt();
    String getText();
    Long getAuthorId();
    String getUsername();
    Integer getLikes();
    Integer getComments();
}
