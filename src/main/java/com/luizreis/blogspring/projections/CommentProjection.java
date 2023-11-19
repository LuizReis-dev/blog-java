package com.luizreis.blogspring.projections;

import java.time.Instant;

public interface CommentProjection {

    Long getId();
    Long getPostId();
    String getText();
    Long getAuthorId();
    String getAuthor();
    Instant getPostedAt();
}
