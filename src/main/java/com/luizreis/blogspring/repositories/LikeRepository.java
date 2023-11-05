package com.luizreis.blogspring.repositories;

import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.domain.like.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikePK> {
}
