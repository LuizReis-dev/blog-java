package com.luizreis.blogspring.repositories;

import com.luizreis.blogspring.domain.follow.UserFollow;
import com.luizreis.blogspring.domain.follow.UserFollowPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, UserFollowPK> {
}
