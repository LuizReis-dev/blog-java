package com.luizreis.blogspring.repositories;

import com.luizreis.blogspring.domain.like.Like;
import com.luizreis.blogspring.domain.like.LikePK;
import com.luizreis.blogspring.projections.LikeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikePK> {

    @Query(nativeQuery = true, value =
                "SELECT " +
                "   tb_likes.user_id as userId, " +
                "   tb_likes.post_id as postId, " +
                "   tb_likes.liked_at as likedAt, " +
                "   tb_users.username " +
                "FROM " +
                "   tb_likes " +
                "INNER JOIN " +
                "   tb_users ON tb_users.id = tb_likes.user_id " +
                "WHERE " +
                "   tb_likes.post_id = :postId")
    List<LikeProjection> selectLikesByPost(Long postId);
}
