package com.luizreis.blogspring.repositories;

import com.luizreis.blogspring.domain.post.Post;
import com.luizreis.blogspring.projections.PostMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value =
            "SELECT " +
            "    tb_posts.id, " +
            "    tb_posts.posted_at as postedAt, " +
            "    tb_posts.author_id as authorId, " +
            "    tb_posts.text, " +
            "    tb_users.username, " +
            "    (SELECT COUNT(*) FROM tb_likes WHERE tb_likes.post_id = tb_posts.id) AS likes, " +
            "    (SELECT COUNT(*) FROM tb_comments WHERE tb_comments.post_id = tb_posts.id) AS comments " +
            "FROM " +
            "    tb_posts " +
            "JOIN " +
            "    tb_users ON tb_users.id = tb_posts.author_id " +
            "JOIN " +
            "    tb_user_follow ON tb_user_follow.followed_user_id = tb_posts.author_id " +
            "WHERE " +
            "    tb_user_follow.user_id = :userId")
    List<PostMinProjection> selectUserTimelinePosts(Long userId);

    @Query(nativeQuery = true, value =
            "SELECT " +
            "    tb_posts.id, " +
            "    tb_posts.posted_at as postedAt, " +
            "    tb_posts.author_id as authorId, " +
            "    tb_posts.text, " +
            "    tb_users.username, " +
            "    (SELECT COUNT(*) FROM tb_likes WHERE tb_likes.post_id = tb_posts.id) AS likes, " +
            "    (SELECT COUNT(*) FROM tb_comments WHERE tb_comments.post_id = tb_posts.id) AS comments " +
            "FROM " +
            "    tb_posts " +
            "JOIN " +
            "    tb_users ON tb_users.id = tb_posts.author_id " +
            "WHERE " +
            "    tb_posts.author_id = :userId")
    List<PostMinProjection> selectPostsByUser(Long userId);
}
